package by.filippov.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import by.filippov.library.connection.WrapperConnection;
import by.filippov.library.entety.Publication;
import by.filippov.library.exception.DaoException;

public class PublicationDao extends AbstractDao<Integer, Publication> {

	private static final String FIND_BY_ISBN = "SELECT * FROM Publication"
			+ " WHERE isbn=?";
	private static final String FIND_BY_GENRE = "SELECT * FROM Publication"
			+ " WHERE genre=?";
	private static final String FIND_BY_TITLE = "SELECT * FROM Publication"
			+ " WHERE title=?";
	private static final String FIND_BY_AUTHOR = "SELECT * FROM Publication"
			+ " WHERE author=?";
	private static final String GENERAL_FIND = "SELECT * FROM Publication "
			+ "WHERE genre=? AND title=? AND author=? AND format=? "
			+ "AND publishing_year<? AND publishing_year>?";

	public PublicationDao(WrapperConnection connection) {
		super(connection);
	}

	@Override
	public Optional<Publication> findEntityById(Integer id) throws DaoException {
		try {
			PreparedStatement st = connection.prepareStatement(FIND_BY_ISBN);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				return Optional.of(extractNextPublication(result));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public ArrayList<Publication> findByAuthor(String author)
			throws DaoException {
		try {
			PreparedStatement st = connection.prepareStatement(FIND_BY_AUTHOR);
			st.setString(1, author);
			ResultSet result = st.executeQuery();
			return extractDataFromResultSet(result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public ArrayList<Publication> findByGenre(String genre) throws DaoException {
		try {
			PreparedStatement st = connection.prepareStatement(FIND_BY_GENRE);
			st.setString(1, genre);
			ResultSet result = st.executeQuery();
			return extractDataFromResultSet(result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public ArrayList<Publication> findByTitle(String title) throws DaoException {
		try {
			PreparedStatement st = connection.prepareStatement(FIND_BY_TITLE);
			st.setString(1, title);
			ResultSet result = st.executeQuery();
			return extractDataFromResultSet(result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public ArrayList<Publication> generalFind(String genre, String title,
			String author, String format, int minYear, int maxYear)
			throws DaoException {
		if (genre == null) {
			genre = "*";
		}
		if (title == null) {
			title = "*";
		}
		if (author == null) {
			author = "*";
		}
		if (format == null) {
			format = "*";
		}
		try {
			PreparedStatement st = connection.prepareStatement(GENERAL_FIND);
			st.setString(1, genre);
			st.setString(2, title);
			st.setString(3, author);
			st.setString(4, format);
			st.setInt(5, minYear);
			st.setInt(6, maxYear);
			ResultSet result = st.executeQuery();
			return extractDataFromResultSet(result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public boolean delete(Publication entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Publication entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Publication entity) throws DaoException {
		// TODO Auto-generated method stub
		return;
	}

	private ArrayList<Publication> extractDataFromResultSet(ResultSet result)
			throws SQLException {
		ArrayList<Publication> publications = new ArrayList<Publication>();
		while (result.next()) {
			Publication publication = extractNextPublication(result);
			publications.add(publication);
		}
		return publications;
	}

	private Publication extractNextPublication(ResultSet result)
			throws SQLException {
		Publication publication = new Publication();
		publication.setIsbn(result.getInt(1));
		publication.setTitle(result.getString(2));
		publication.setPublishYear(result.getInt(3));
		publication.setAuthor(result.getString(4));
		publication.setGenre(result.getString(5));
		publication.setFormat(result.getString(6));
		publication.setAnnotation(result.getString(7));
		return publication;
	}

}
