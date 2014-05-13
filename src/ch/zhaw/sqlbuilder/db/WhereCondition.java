package ch.zhaw.sqlbuilder.db;

import ch.zhaw.sqlbuilder.builders.SqlHelpers;

/**
 * Holds whereConditions, so that the builder can construct the where clause
 * later on.
 * 
 * @author Simon Aebersold
 * 
 */
public class WhereCondition {

	private final String field;
	private final String operator;
	private final String clause;

	/**
	 * Constructor.
	 * 
	 * @param clause
	 *            full where clause. should be valid SQL syntax e.g. id=1.
	 */
	public WhereCondition(String clause) {
		this.field = null;
		this.operator = null;
		this.clause = clause;
	}

	/**
	 * Constructor. Use for matching operations. E.g. id=1
	 * 
	 * @param field
	 *            the field to be checked e.g. id
	 * @param clause
	 *            second part of where clause e.g. 1
	 */
	public WhereCondition(String field, String clause) {
		this.field = field;
		this.operator = "=";
		this.clause = clause;
	}

	/**
	 * Constructor.
	 * 
	 * @param field
	 *            the field to be checked e.g. id
	 * @param operator
	 *            the operator of the where clause e.g. = or >
	 * @param clause
	 *            second part of where clause e.g. 1
	 * 
	 */
	public WhereCondition(String field, String operator, String clause) {
		this.field = field;
		this.operator = operator;
		this.clause = clause;
	}

	public String getField() {
		return field;
	}

	public String getOperator() {
		return operator;
	}

	public String getClause() {
		return clause;
	}

	/*
	 * used in the builder
	 */
	@Override
	public String toString() {

		if (field == null && operator == null) {
			return clause;
		} else if (operator.equals("NOESCAPE")) {
			return field + " = " + clause;
		} else {
			return field + " " + operator + " "
					+ SqlHelpers.stringToStringQuoted(clause);
		}

	}
}
