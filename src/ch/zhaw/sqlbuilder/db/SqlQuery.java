package ch.zhaw.sqlbuilder.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import org.apache.log4j.Logger;

import com.sun.rowset.CachedRowSetImpl;

/**
 * Represents an executable SQL-Query. Make sure to use the right execute
 * method.
 * 
 * @author Yanick Lukic
 */
public class SqlQuery {

	private final String query;

	/**
	 * Constructor.
	 * 
	 * @param builder
	 *            The builder which contains all the information for the
	 *            SQL-Query.
	 * @param query
	 *            The generated SQL query, ready to execute
	 */
	public SqlQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	private PreparedStatement execute(Connection connection)
			throws SQLException {
		PreparedStatement ps = null;
		// Log query for console
		Logger.getRootLogger().debug(query);
		ps = connection.prepareStatement(query);
		ps.execute();
		return ps;
	}

	/**
	 * Executes the query and returns a row set. Should be used for
	 * SELECT-statements.
	 * 
	 * @return The row set returned from the database or null if the SQL-call
	 *         failed.
	 */
	public RowSet executeQuery() {
		Connection connection = DatabaseConnectionFactory.getSQLConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		CachedRowSetImpl rowSet = null;
		try {
			ps = execute(connection);
			rs = ps.getResultSet();
			rowSet = new CachedRowSetImpl();
			rowSet.populate(rs);
		} catch (SQLException e) {
			Logger.getLogger(SqlQuery.class).error(
					"DB connection lost while executing query.", e);
		} finally {
			DatabaseConnectionFactory.close(ps);
			DatabaseConnectionFactory.close(rs);
			DatabaseConnectionFactory.closeConnection(connection);
		}
		return rowSet;
	}

	/**
	 * Executes the query and returns the count of updates. Should be used for
	 * INSERT-, UPDATE- and DELETE-statements.
	 * 
	 * @return The update count returned from the database or -1 if there are no
	 *         more results.
	 */
	public int executeUpdate() {
		Connection connection = DatabaseConnectionFactory.getSQLConnection();
		PreparedStatement ps = null;
		int updateCount = 0;
		try {
			ps = execute(connection);
			updateCount = ps.getUpdateCount();
		} catch (SQLException e) {
			Logger.getLogger(SqlQuery.class).error(
					"DB connection lost while updating.");
		} finally {
			DatabaseConnectionFactory.close(ps);
			DatabaseConnectionFactory.closeConnection(connection);
		}
		return updateCount;
	}
}
