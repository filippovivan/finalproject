package by.filippov.library.command;

import static by.filippov.library.utils.JspManager.*;

import java.util.List;

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

public class RegisterCommand implements Command {
	private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

	@Override
	public String execute(RequestContent content) {
		User user = new User();
		user.setLogin(content.getParameter("login"));
		String password = content.getParameter("password");
		user.setPassword(PasswordChecker.encodePassword(password));
		user.setName(content.getParameter("username"));
		user.setType(User.UserType.USER);
		try (WrapperConnection connection = ConnectionPool.getInstance()
				.takeConnection()) {
			UserDao dao = new UserDao(connection);
			if (dao.create(user)) {
				content.getSession().setAttribute("user", user);
				CatalogueDao catalogueDao = new CatalogueDao(connection);
				List<Catalogue> catalogueList = catalogueDao.findFromTo(0, 10);
				content.setAttribute("catalogues", catalogueList);
				return getProperty("main.cataloguelist");
			} else {
				content.setAttribute("notFree", "This login not free");
				return getProperty("main.registration");
			}
		} catch (DaoException e) {
			LOG.error(e);
			return getProperty("error.error");
		}
	}
}
