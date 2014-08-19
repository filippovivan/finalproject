package by.filippov.library.command;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import by.filippov.library.connection.ConnectionPool;
import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.controller.RequestContent;
import by.filippov.library.dao.CatalogueDao;
import by.filippov.library.dao.UserDao;
import by.filippov.library.entety.Catalogue;
import by.filippov.library.entety.User;
import by.filippov.library.entety.utils.PasswordChecker;
import by.filippov.library.exception.DaoException;
import by.filippov.library.utils.JspManager;

public class LoginCommand implements Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(RequestContent content) {
		String login = content.getParameter("login");
		String password;
		password = PasswordChecker.encodePassword(content
				.getParameter("password"));
		try (WrapperConnection connection = ConnectionPool.getInstance()
				.takeConnection()) {
			UserDao dao = new UserDao(connection);
			Optional<User> answer = dao.findLogin(login, password);
			if (answer.isPresent()) {
				User user = answer.get();
				content.getSession().setAttribute("user", user);
				switch (user.getType()) {
				case USER:
					CatalogueDao catalogueDao = new CatalogueDao(connection);
					List<Catalogue> catalogueList = catalogueDao.findFromTo(0,
							20);
					content.setAttribute("catalogues", catalogueList);
					return JspManager.getProperty("main.cataloguelist");
				case LIBRARIST:
					// TODO return init librarist page
					return null;
				case ADMIN:
					List<User> librarists = dao.findLibrarists();
					content.setAttribute("librarists", librarists);
					return JspManager.getProperty("admin.libraristview");
				default:
					// TODO error
					LOG.error("Invalid user type occured");
					return JspManager.getProperty("error.error");
				}

			} else {
				//TODO 
				content.setAttribute("noSuchUser",
						"<br>Incorrect login/password pair");
				return JspManager.getProperty("main.login");
			}

		} catch (DaoException e) {
			// TODO Apply error info.
			LOG.error(e);
			return JspManager.getProperty("error.error");
		}
	}
}
