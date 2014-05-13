package ch.zhaw.sqlbuilder.builders;

import java.sql.SQLException;

import ch.zhaw.sqlbuilder.db.SqlQuery;
import ch.zhaw.sqlbuilder.db.WhereCondition;

/**
 * SQL Builder for UPDATE-requests.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public class SqlBuilderUpdate extends SqlBuilderBase {

	@Override
	public SqlBuilderBase fields(String... fields) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	public SqlBuilderBase offset(int offset) {
		throw new UnsupportedOperationException(
				"Invalid option for insert-operator");
	}

	@Override
	public SqlBuilderBase order(String orderField, String orderDirection) {
		throw new UnsupportedOperationException(
				"Invalid option for delete-operator");
	}

	/**
	 * builds UPDATE statement
	 */
	@Override
	public SqlQuery build() throws SQLException {
		StringBuilder query = new StringBuilder();

		if (this.getValues().isEmpty()) {
			throw new SQLException("no fields specified for UPDATE");
		}

		// Select Fields
		query.append("UPDATE ");

		// Tables
		if (this.getTables() == null) {
			throw new SQLException("no table specified in UPDATE statement");
		} else {
			SqlHelpers.arrayToCommaString(query, this.getTables());
		}

		// Set Values
		query.append(" SET ");
		int elementCounterSet = 1;

		for (String key : this.getValues().keySet()) {
			query.append(key);
			query.append(" = ");
			query.append(SqlHelpers.stringToStringQuoted(this.getValues().get(
					key)));

			// append "," to n-1 elements
			if (elementCounterSet < this.getValues().size()) {
				query.append(", ");
			}
			elementCounterSet++;
		}

		// WHERE Statements
		if (this.getWhereConditions().size() > 0) {
			query.append(" WHERE ");
			int elementCounterWhere = 1;

			for (WhereCondition where : this.getWhereConditions()) {
				query.append(where.toString());

				// append "AND" to n-1 elements
				if (elementCounterWhere < this.getWhereConditions().size()) {
					query.append(" AND "); // default is AND where's
				}

				elementCounterWhere++;
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
