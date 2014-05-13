package ch.zhaw.sqlbuilder.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Factory class for database connection.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public class DatabaseConnectionFactory {

	/**
	 * Loads the JDBC driver for MYSQL.
	 */
	private static void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			Logger.getLogger(DatabaseConnectionFactory.class).error(
					"Couldn't load JDBC driver.", e);
		}
	}

	/**
	 * Creates database connection and returns it.
	 * 
	 * @return The database connection.
	 */
	static Connection getSQLConnection() {
		loadDriver();

		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties"));
		} catch (IOException e) {
			Logger.getLogger(DatabaseConnectionFactory.class).error(
					"Couldn't read config.properties.", e);
		}

		String url = properties.getProperty("dbUrl");
		String db = properties.getProperty("dbName");
		String user = properties.getProperty("dbUser");
		String pw = properties.getProperty("dbPassword");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url + db, user, pw);
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConnectionFactory.class).error(
					"Couldn't get db connection.", e);
		}

		return connection;
	}

	/**
	 * Closes the current statement.
	 * 
	 * @param statement
	 *            The statement.
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				Logger.getLogger(DatabaseConnectionFactory.class).error(
						"Couldn't close statement.", e);
			}
		}
	}

	/**
	 * Closes the current resultset.
	 * 
	 * @param rs
	 *            The result set.
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				Logger.getLogger(DatabaseConnectionFactory.class).error(
						"Couldn't close result set.", e);
			}
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConnectionFactory.class).error(
					"Couldn't close connection.", e);
		}
	}
}
