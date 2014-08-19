package by.filippov.library.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import by.filippov.library.exception.DaoException;

public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	private final int CONNECTIONS_NUMBER;
	private ArrayBlockingQueue<WrapperConnection> freeConnections;
	private ArrayList<Connection> allConnections;

	private static class PoolHandler {
		private static final ConnectionPool INSTANCE = new ConnectionPool();
	}

	public static ConnectionPool getInstance() {
		return PoolHandler.INSTANCE;
	}

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			ResourceBundle resource = ResourceBundle
					.getBundle("properties.database");
			String url = resource.getString("url");
			String user = resource.getString("user");
			String pass = resource.getString("password");
			CONNECTIONS_NUMBER = Integer.valueOf(resource.getString("size"));
			Properties prop = new Properties();
			prop.put("user", user);
			prop.put("password", pass);
			freeConnections = new ArrayBlockingQueue<WrapperConnection>(
					CONNECTIONS_NUMBER);
			allConnections = new ArrayList<Connection>(CONNECTIONS_NUMBER);
			for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
				Connection connection = DriverManager.getConnection(url, prop);
				freeConnections.add(new WrapperConnection(connection));
				allConnections.add(connection);
			}
		} catch (SQLException e) {
			LOG.error("Can't establish WrapperConnection to database, casue: \n"
					+ e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public WrapperConnection takeConnection() throws DaoException {
		try {
			return freeConnections.take();
		} catch (InterruptedException e) {
			throw new DaoException(e);
		}
	}

	public WrapperConnection takeConnection(int timeout, TimeUnit timeUnit)
			throws DaoException {
		try {
			WrapperConnection conn = freeConnections.poll(timeout, timeUnit);
			if (conn == null) {
				throw new DaoException("Out of waiting time.");
			}
			return conn;
		} catch (InterruptedException e) {
			throw new DaoException(e);
		}
	}

	public boolean returnConnection(WrapperConnection connection) {
		try {
			freeConnections.put(connection);
			return true;
		} catch (InterruptedException e) {
			LOG.error("Too much connections occured");
			return false;
		}
	}

	public void destroy() {
		for (Connection connection : allConnections) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Error while closing connection pool.", e);
			}
		}
	}
}
