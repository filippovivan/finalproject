package by.filippov.library.dao;

import java.sql.Connection;
import java.util.Optional;

import by.filippov.library.entety.Entity;
import by.filippov.library.exception.DaoException;

public abstract class AbstractDao<K, T extends Entity> {
	public static final int TIMEOUT = 1000;
	protected Connection connection;

	public AbstractDao(Connection connection2) {
		this.connection = connection2;
	}

	public abstract Optional<T> findEntityById(K id) throws DaoException;

	public abstract void delete(K id) throws DaoException;

	public abstract boolean delete(T entity) throws DaoException;

	public abstract boolean create(T entity) throws DaoException;

	public abstract void update(T entity) throws DaoException;

}
