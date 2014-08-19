package by.filippov.library.command;

import java.util.List;

import org.apache.log4j.Logger;

import by.filippov.library.connection.ConnectionPool;
import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.controller.RequestContent;
import by.filippov.library.dao.UserDao;
import by.filippov.library.entety.User;
import by.filippov.library.exception.DaoException;
import by.filippov.library.utils.JspManager;

public class CreateLibraristCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(CreateLibraristCommand.class);

	@Override
	public String execute(RequestContent content) {
		String login = content.getParameter("login");
		String name = content.getParameter("name");
		String password = content.getParameter("password");
		String passwordrepeat = content.getParameter("passwordrepeat");
		if (password.equals(passwordrepeat)) {
			User librarist = new User(login, password, User.UserType.LIBRARIST,
					name);
			try (WrapperConnection connection = ConnectionPool.getInstance()
					.takeConnection()) {
				UserDao dao = new UserDao(connection);
				if (dao.create(librarist)) {
					// TODO messages
				} else {
					// TODO
				}
				List<User> librarists = dao.findLibrarists();
				content.setAttribute("librarists", librarists);
			} catch (DaoException e) {
				LOG.error(e);
				return JspManager.getProperty("error.error");
			}
		}
		// TODO mismatch
		return JspManager.getProperty("admin.libraristview");
	}

}
