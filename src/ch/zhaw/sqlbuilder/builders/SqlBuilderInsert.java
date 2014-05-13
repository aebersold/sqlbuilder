package ch.zhaw.sqlbuilder.builders;

import java.sql.SQLException;

import ch.zhaw.sqlbuilder.db.SqlQuery;

/**
 * SQL Builder for Insert-requests.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public class SqlBuilderInsert extends SqlBuilderBase {

	@Override
	public SqlBuilderBase where(String field, String clause) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	public SqlBuilderBase where(String field, String operator, String clause) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	public SqlBuilderBase order(String orderField, String orderDirection) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	public SqlBuilderBase limit(int limit) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	public SqlBuilderBase offset(int offset) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	/**
	 * Inserts data into a table. Note: supports currently only one row at a time.
	 * If needed, add functionality later on.
	 */
	public SqlQuery build() throws SQLException {
		StringBuilder query = new StringBuilder();

		if (this.getValues().isEmpty()) {
			throw new SQLException("no fields specified for INSERT");
		}

		query.append("INSERT ");

		// Table
		query.append("INTO ");

		if (this.getTables() == null) {
			throw new SQLException("no table specified in INSERT statement");
		} else {
			SqlHelpers.arrayToCommaString(query, this.getTables());
		}

		// Specify Fields
		query.append(" (");

		String fields[] = this.getValues().keySet().toArray(new String[0]);
		SqlHelpers.arrayToCommaString(query, fields);

		query.append(")");

		// Values
		query.append(" VALUES ");

		query.append("(");

		String values[] = this.getValues().values().toArray(new String[0]);
		SqlHelpers.arrayToCommaStringQuoted(query, values);

		query.append(")");

		return new SqlQuery(query.toString());
	}

}
