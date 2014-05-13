package ch.zhaw.sqlbuilder.db;

import ch.zhaw.sqlbuilder.builders.SqlBuilderDelete;
import ch.zhaw.sqlbuilder.builders.SqlBuilderInsert;
import ch.zhaw.sqlbuilder.builders.SqlBuilderSelect;
import ch.zhaw.sqlbuilder.builders.SqlBuilderUpdate;

/**
 * Factory class which contains all the necessary SQL-actions. For fancy
 * SQL-statements use the static import on these methods.
 * 
 * @author Yanick Lukic
 */
public class SqlActionFactory {

	/**
	 * Creates a SQL-Builder object for a SELECT-Request.
	 * 
	 * @return The SQL-SELECT-Builder.
	 */
	public static SqlBuilderSelect select() {
		return new SqlBuilderSelect();
	}

	/**
	 * Creates a SQL-Builder object for a UPDATE-Request.
	 * 
	 * @return The SQL-UPDATE-Builder.
	 */
	public static SqlBuilderUpdate update() {
		return new SqlBuilderUpdate();
	}

	/**
	 * Creates a SQL-Builder object for a INSERT-Request.
	 * 
	 * @return The SQL-INSERT-Builder.
	 */
	public static SqlBuilderInsert insert() {
		return new SqlBuilderInsert();
	}

	/**
	 * Creates a SQL-Builder object for a DELETE-Request.
	 * 
	 * @return The SQL-DELETE-Builder.
	 */
	public static SqlBuilderDelete delete() {
		return new SqlBuilderDelete();
	}

}
