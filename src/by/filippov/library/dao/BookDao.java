package by.filippov.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import by.filippov.library.entety.Book;
import by.filippov.library.entety.utils.TakeType;
import by.filippov.library.exception.DaoException;

public class BookDao extends AbstractDao<Integer, Book> {

	private static final String COUNT_FREE_BOOKS = "SELECT COUNT(id) FROM book WHERE"
			+ " isbn=? AND id NOT IN(SELECT id_book FROM Granted)";
	private static final String TAKE_BOOK = "INSERT INTO `library`.`granted` "
			+ "(`id_book`, `granted_type`, `User_login`) VALUES (?, ?, ?)";
	private static final String FIND_BEST_FREE_BOOK = "SELECT id FROM Book "
			+ "WHERE isbn=? AND id NOT IN (SELECT id_book FROM Granted)"
			+ " ORDER BY defect LIMIT 1";

	public BookDao(Connection connection) {
		super(connection);
	}

	@Override
	public Optional<Book> findEntityById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public boolean delete(Book entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(Book entity) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Book entity) throws DaoException {
		// TODO Auto-generated method stub

	}

	public int countFreeBooks(int isbn) throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(COUNT_FREE_BOOKS)) {
			statement.setInt(1, isbn);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return result.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public boolean takeBook(int isbn, TakeType takeType, String login)
			throws DaoException {
		int id = findBestFreeBook(isbn);
		if (id >= 0) {
			try (PreparedStatement statement = connection
					.prepareStatement(TAKE_BOOK)) {
				statement.setInt(1, id);
				statement.setString(2, takeType.toString());
				statement.setString(3, login);
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return false;
	}

	private int findBestFreeBook(int isbn) throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(FIND_BEST_FREE_BOOK)) {
			statement.setInt(1, isbn);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return result.getInt(1);
			}
			return -1;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
