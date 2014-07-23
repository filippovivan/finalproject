package by.filippov.library.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import by.filippov.library.exceptions.DAOException;

public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

	private static final int WrapperConnectionS_NUMBER = 10;
	private ArrayBlockingQueue<WrapperConnection> connections = new ArrayBlockingQueue<WrapperConnection>(
			WrapperConnectionS_NUMBER);

	private static class PoolHandler {
		private static final ConnectionPool INSTANCE = new ConnectionPool();
	}

	public static ConnectionPool getInstance() {
		return PoolHandler.INSTANCE;
	}

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			for (int i = 0; i < WrapperConnectionS_NUMBER; i++) {
				connections.add(new WrapperConnection(DriverManager
						.getConnection("jdbc:mysql://localhost:3306/library",
								"root", "salo")));
			}
		} catch (SQLException e) {
			LOG.error("Can't establish WrapperConnection to database, casue: \n"
					+ e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public WrapperConnection takeWrapperConnection() throws DAOException {
		try {
			return connections.take();
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}
	}

	public void returnWrapperConnection(WrapperConnection conn) {
		try {
			connections.put(conn);
		} catch (InterruptedException e) {
			LOG.error("Too much WrapperConnections occured");
		}
	}
}
