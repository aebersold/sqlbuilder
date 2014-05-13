package ch.zhaw.sqlbuilder.builders;

import java.sql.SQLException;

import ch.zhaw.sqlbuilder.db.SqlQuery;
import ch.zhaw.sqlbuilder.db.WhereCondition;

/**
 * SQL Builder for SELECT-requests.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public class SqlBuilderSelect extends SqlBuilderBase {

	@Override
	public SqlBuilderBase values(String... values) {
		throw new UnsupportedOperationException(
				"Invalid option for select-operator");
	}

	/**
	 * builds SELECT statement.
	 */
	@Override
	public SqlQuery build() throws SQLException {
		StringBuilder query = new StringBuilder();

		// Select Fields
		query.append("SELECT ");

		if (this.getFields() == null) {
			query.append("*");
		} else {
			SqlHelpers.arrayToCommaString(query, this.getFields());
		}

		// Select Tables
		query.append(" FROM ");

		if (this.getTables() == null) {
			throw new SQLException("no table specified in SELECT statement");
		} else {
			SqlHelpers.arrayToCommaString(query, this.getTables());
		}

		// WHERE Statements
		if (this.getWhereConditions().size() > 0) {
			query.append(" WHERE ");
			int whereCommaCounter = 1;

			for (WhereCondition where : this.getWhereConditions()) {
				query.append(where.toString());

				// append "AND" to n-1 elements
				if (whereCommaCounter < this.getWhereConditions().size()) {
					query.append(" AND "); // default is AND where's
				}
				whereCommaCounter++;
			}
		}

		// GROUP BY
		if (this.getGroupBy() != null) {
			query.append(" GROUP BY ");
			query.append(this.getGroupBy());
		}

		// ORDER BY
		if (this.getOrderField() != null) {
			query.append(" ORDER BY ");
			query.append(this.getOrderField());

			if (this.getOrderDirection() != null) {
				query.append(" ");
				query.append(this.getOrderDirection());
			}
		}

		// LIMIT
		if (this.getLimit() > 0) {
			query.append(" LIMIT ");

			if (this.getOffset() > 0) {
				query.append(this.getOffset());
				query.append(", ");
				query.append(this.getLimit());
			} else {
				query.append(this.getLimit());
			}
		}

		return new SqlQuery(query.toString());
	}

}
