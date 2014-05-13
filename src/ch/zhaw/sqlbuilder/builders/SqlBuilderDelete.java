package ch.zhaw.sqlbuilder.builders;

import java.sql.SQLException;

import ch.zhaw.sqlbuilder.db.SqlQuery;
import ch.zhaw.sqlbuilder.db.WhereCondition;

/**
 * SQL Builder for Delete-requests.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public class SqlBuilderDelete extends SqlBuilderBase {

	@Override
	public SqlBuilderBase fields(String... fields) {
		throw new UnsupportedOperationException(
				"Invalid option for delete-operator");
	}

	@Override
	public SqlBuilderBase values(String... fields) {
		throw new UnsupportedOperationException(
				"Invalid option for delete-operator");
	}

	@Override
	public SqlBuilderBase order(String orderField, String orderDirection) {
		throw new UnsupportedOperationException(
				"Invalid option for delete-operator");
	}

	@Override
	public SqlBuilderBase offset(int offset) {
		throw new UnsupportedOperationException(
				"Invalid option for delete-operator");
	}

	@Override
	/**
	 * Builds an SQLQuery object to delete from a table. Be careful.
	 */
	public SqlQuery build() throws SQLException {
		StringBuilder query = new StringBuilder();
		int whereCommaCounter = 1;

		query.append("DELETE ");

		// Table
		query.append("FROM ");

		if (this.getTables() == null) {
			throw new SQLException("no table specified in DELETE statement");
		} else {
			SqlHelpers.arrayToCommaString(query, this.getTables());
		}

		// WHERE Statements
		if (this.getWhereConditions().size() > 0) {
			query.append(" WHERE ");
			whereCommaCounter = 1;

			for (WhereCondition where : this.getWhereConditions()) {
				query.append(where.toString());

				// append "AND" to n-1 elements
				if (whereCommaCounter < this.getWhereConditions().size()) {
					query.append(" AND "); // default is AND where's
				}
				whereCommaCounter++;
			}
		}

		// LIMIT
		if (this.getLimit() > 0) {
			query.append(" LIMIT ");
			query.append(this.getLimit());
		}

		return new SqlQuery(query.toString());
	}

}
