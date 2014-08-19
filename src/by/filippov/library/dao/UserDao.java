package by.filippov.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.filippov.library.entety.User;
import by.filippov.library.exception.DaoException;

public class UserDao extends AbstractDao<String, User> {

	private static final String UPDATE_USER = "UPDATE User WHERE login = ? VALUES(?, ?, ?, ?)";
	private static final String CREATE_USER = "INSERT INTO `library`.`user`"
			+ " (`login`, `password`, `type`, `name`)" + " VALUES (?, ?, ?, ?)";
	private static final String FIND_LOGIN = "SELECT * FROM User WHERE login = ?"
			+ " AND password = ?";
	private static final String CHECK_LOGIN = "SELECT * FROM User WHERE login = ?";
	private static final String FIND_LIBRARIST = "SELECT * FROM User WHERE"
			+ " type='LIBRARIST'";
	private static final String REMOVE_USER = "DELETE FROM User WHERE login =?";

	public UserDao(Connection connection) {
		super(connection);
	}

	@Override
	public Optional<User> findEntityById(String id) throws DaoException {
		throw new DaoException("Operation not allowed.");
	}

	public Optional<User> findLogin(String login, String password)
			throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(FIND_LOGIN)) {
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return Optional.of(extractNextUser(result));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(String login) throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(REMOVE_USER)) {
			statement.setString(1, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delete(User entity) throws DaoException {
		throw new DaoException("Operation not allowed.");
	}

	@Override
	public boolean create(User user) throws DaoException {
		try (PreparedStatement findStatement = connection
				.prepareStatement(CHECK_LOGIN)) {
			findStatement.setString(1, user.getLogin());
			ResultSet result = findStatement.executeQuery();
			if (result.next()) {
				return false;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		try (PreparedStatement statement = connection
				.prepareStatement(CREATE_USER)) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getType().toString());
			statement.setString(4, user.getName());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DaoException("Problem while creating user", e);
		}
	}

	@Override
	public void update(User user) throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(UPDATE_USER)) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getType().toString());
			statement.setString(4, user.getName());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Failed to update, cause:", e);
		}
	}

	private User extractNextUser(ResultSet result) throws SQLException {
		User user = new User();
		user.setLogin(result.getString(1));
		user.setPassword(result.getString(2));
		user.setType(result.getString(3));
		user.setName(result.getString(4));
		return user;
	}

	public List<User> findLibrarists() throws DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement(FIND_LIBRARIST)) {
			ArrayList<User> librarists = new ArrayList<User>();
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				librarists.add(extractNextUser(result));
			}
			return librarists;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
