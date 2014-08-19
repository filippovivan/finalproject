package by.filippov.library.command;

import java.util.Optional;

import org.apache.log4j.Logger;

import by.filippov.library.connection.ConnectionPool;
import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.controller.RequestContent;
import by.filippov.library.dao.BookDao;
import by.filippov.library.dao.PublicationDao;
import by.filippov.library.entety.Publication;
import by.filippov.library.exception.DaoException;
import by.filippov.library.utils.JspManager;

public class PublicationChoosenCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(PublicationChoosenCommand.class);

	@Override
	public String execute(RequestContent content) {
		int isbn = Integer.valueOf(content.getParameter("publication"));
		try (WrapperConnection connection = ConnectionPool.getInstance()
				.takeConnection()) {
			PublicationDao dao = new PublicationDao(connection);
			Optional<Publication> result = dao.findEntityById(isbn);
			if (result.isPresent()) {
				Publication publication = result.get();
				content.setAttribute("publication", publication);
				int countBooks = new BookDao(connection).countFreeBooks(isbn);
				content.setAttribute("bookcount", countBooks);
				return JspManager.getProperty("main.publicationview");
			} else {
				// TODO not found
				return JspManager.getProperty("error.error");
			}
		} catch (DaoException e) {
			LOG.error(e);
			return JspManager.getProperty("error.error");
		}
	}

}
