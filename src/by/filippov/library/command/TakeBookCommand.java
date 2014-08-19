package by.filippov.library.command;

import org.apache.log4j.Logger;

import by.filippov.library.connection.ConnectionPool;
import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.controller.RequestContent;
import by.filippov.library.dao.BookDao;
import by.filippov.library.entety.User;
import by.filippov.library.entety.utils.TakeType;
import by.filippov.library.exception.DaoException;
import by.filippov.library.utils.JspManager;

public class TakeBookCommand implements Command {

	private static final Logger LOG = Logger.getLogger(TakeBookCommand.class);

	@Override
	public String execute(RequestContent content) {
		TakeType takeType = TakeType.valueOf(content
				.getParameter("giveoutType"));
		int isbn = Integer.valueOf(content.getParameter("isbn"));
		String login = ((User) content.getSession().getAttribute("user"))
				.getLogin();
		try (WrapperConnection connection = ConnectionPool.getInstance()
				.takeConnection()) {
			BookDao dao = new BookDao(connection);
			if (!dao.takeBook(isbn, takeType, login)) {
				content.setAttribute("noFreeBooks", true);
			}
			return JspManager.getProperty("main.mybooks");
		} catch (DaoException e) {
			LOG.error(e);
			return JspManager.getProperty("error.error");
		}
	}

}
