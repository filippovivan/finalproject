package by.filippov.library.command;

import java.util.List;
import java.util.Optional;

import by.filippov.library.connection.ConnectionPool;
import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.controller.RequestContent;
import by.filippov.library.dao.CatalogueDao;
import by.filippov.library.entety.Catalogue;
import by.filippov.library.entety.Publication;
import by.filippov.library.exception.DaoException;
import by.filippov.library.utils.JspManager;

public class CatalogueChoosenCommand implements Command {

	@Override
	public String execute(RequestContent content) {
		int catalogueId = Integer.valueOf(content.getParameter("catalogue"));
		try (WrapperConnection connection = ConnectionPool.getInstance()
				.takeConnection()) {
			CatalogueDao dao = new CatalogueDao(connection);
			Optional<Catalogue> optional = dao.findEntityById(catalogueId);
			if (optional.isPresent()) {
				Catalogue catalogue = optional.get();
				content.setAttribute("catalogue", catalogue);
				List<Publication> publications = dao
						.findPublications(catalogueId);
				content.setAttribute("publications", publications);
				return JspManager.getProperty("main.catalogueview");
			} else {
				// TODO Not found
				return JspManager.getProperty("error.error");
			}
		} catch (DaoException e) {
			// TODO Apply error info.
			return JspManager.getProperty("error.error");
		}
	}
}
