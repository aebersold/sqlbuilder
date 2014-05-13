package ch.zhaw.sqlbuilder.builders;

import java.sql.SQLException;

import ch.zhaw.sqlbuilder.db.SqlQuery;

/**
 * SQL Builder interface. Contains all methods for creating SQL-Statements. Use
 * the specific SQL-Builder for the wanted operation.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public interface ISqlBuilder {

	/**
	 * ONLY FOR SELECT. Optional, fields used to specify the columns for a
	 * select statement. If not set, the ORM will generate 'SELECT *'. Not
	 * escaped.
	 */
	public ISqlBuilder fields(String... fields);

	/**
	 * ONLY FOR UPDATE / INSERT. Columns / Values for insert and update command.
	 * Please call this method only once every query.
	 * 
	 * @param values
	 *            String Array of Key/Value Pairs .values("field", "value",
	 *            "field2", "value2", ...)
	 */
	public ISqlBuilder values(String... values);

	/**
	 * Specify a string array of the table(s) for all queries. This will output
	 * an unescaped, comma seperated list in the query. It's also allowed to
	 * write something like 'table as t'.
	 */
	public ISqlBuilder tables(String... tables);

	/**
	 * Adds a where clause with raw statements, multiple allowed. If you want to
	 * specify multiple wheres, use method chaining, eg. .where("1 ==
	 * 1").where("a == b") etc. No escaping whatsoever.
	 */
	public ISqlBuilder where(String clause);

	/**
	 * Adds a where clause with equals operator, multiple allowed through method
	 * chaining. The clause will be escaped, the field will never be escaped.
	 * 
	 * If you write .where("name", "tom") the statement will be 'WHERE name =
	 * "tom"'.
	 */
	public ISqlBuilder where(String field, String clause);

	/**
	 * Adds a where clause with own operator, multiple allowed through method
	 * chaining.
	 * 
	 * If you write .where("price", "<", "42") the statement will be 'WHERE
	 * price < 42'.
	 * 
	 * If you use the string "NOESCAPE" as operator, the clause will not be
	 * escaped. Otherwise the clause will be escaped (default behavior). The
	 * field will never be escaped.
	 */
	public ISqlBuilder where(String field, String operator, String clause);

	/**
	 * Set order field and direction (ASC/DESC). Only one order field per query
	 * allowed at the moment. The field is escaped.
	 */
	public ISqlBuilder order(String orderField, String orderDirection);

	/**
	 * Limit rows of a query. Generates "LIMIT n".
	 */
	public ISqlBuilder limit(int limit);

	/**
	 * Offset for limited queries. Requires a limit() call to work, will
	 * generate "LIMIT offset, limit". Escaped, SQL-Injection safe.
	 */
	public ISqlBuilder offset(int offset);

	/**
	 * Set the field for a GROUP BY. Not escaped.
	 */
	public ISqlBuilder groupBy(String field);

	// here are some additional ideas for builder features.
	// write these additional ORM features, if they will be needed in the
	// future.

	// public ISqlBuilder raw(String str);
	// public ISqlBuilder join(String table, String clause1, String operator,
	// String clause2);
	// public ISqlBuilder orWhere(String field, String operator, String clause);
	// public ISqlBuilder whereNull(String field);
	// public ISqlBuilder whereNotNull(String field);
	// public ISqlBuilder groupBy(String field);
	// public ISqlBuilder having(String field, String operator, String clause);

	/**
	 * Builds the SQL-Query and returns it.
	 * 
	 * @return The built SQL-Query Object.
	 * @throws SQLException
	 */
	public SqlQuery build() throws SQLException;

}
