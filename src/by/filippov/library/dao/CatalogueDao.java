package by.filippov.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.entety.Catalogue;
import by.filippov.library.entety.Publication;
import by.filippov.library.exception.DaoException;

public class CatalogueDao extends AbstractDao<Integer, Catalogue> {

	private static final String FIND_CATALOGUE_BY_ID = "SELECT * FROM Catalogue WHERE id=?";
	private static final String SELECT_FROM_TO = "SELECT * FROM Catalogue ORDER BY id LIMIT ?, ?";
	private static final String FIND_PUBLICATIONS = "SELECT isbn, title, publishing_year,"
			+ " author, genre, format, annotation FROM Catalogue_to_Publication"
			+ " JOIN Publication ON"
			+ " Catalogue_to_Publication.Publication_isbn=Publication.isbn "
			+ "JOIN Catalogue ON"
			+ " Catalogue_to_Publication.Catalogue_id=Catalogue.id WHERE Catalogue.id=?";

	public CatalogueDao(WrapperConnection connection) {
		super(connection);
	}

	@Override
	public Optional<Catalogue> findEntityById(Integer id) throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(FIND_CATALOGUE_BY_ID)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return Optional.of(extractNextCatalogue(result));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Integer id) throws DaoException {
		throw new DaoException("Operation not allowed");
	}

	@Override
	public boolean delete(Catalogue entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Catalogue entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Catalogue entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public List<Catalogue> findFromTo(int from, int to) throws DaoException {
		ArrayList<Catalogue> catalogues = new ArrayList<Catalogue>();
		try (PreparedStatement statement = connection
				.prepareStatement(SELECT_FROM_TO)) {
			statement.setInt(1, from);
			statement.setInt(2, to);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Catalogue catalogue = extractNextCatalogue(result);
				catalogues.add(catalogue);
			}
			return catalogues;
		} catch (SQLException e) {
			throw new DaoException("Problem while extracting catalogue", e);
		}
	}

	private Catalogue extractNextCatalogue(ResultSet result)
			throws SQLException {
		Catalogue catalogue = new Catalogue();
		catalogue.setId(result.getInt(1));
		catalogue.setName(result.getString(2));
		catalogue.setDescription(result.getString(3));
		return catalogue;
	}

	public List<Publication> findPublications(int catalogueId)
			throws DaoException {
		ArrayList<Publication> publications = new ArrayList<Publication>();
		try (PreparedStatement statement = connection
				.prepareStatement(FIND_PUBLICATIONS)) {
			statement.setInt(1, catalogueId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Publication publication = new Publication();
				publication.setIsbn(result.getInt(1));
				publication.setTitle(result.getString(2));
				publication.setPublishYear(result.getInt(3));
				publication.setAuthor(result.getString(4));
				publication.setGenre(result.getString(5));
				publication.setFormat(result.getString(6));
				publication.setAnnotation(result.getString(7));
				publications.add(publication);
			}
			return publications;
		} catch (SQLException e) {
			throw new DaoException("Problem while extraction publications.", e);
		}
	}

}
