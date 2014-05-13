package ch.zhaw.sqlbuilder.builders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import ch.zhaw.sqlbuilder.db.SqlQuery;
import ch.zhaw.sqlbuilder.db.WhereCondition;
import ch.zhaw.sqlbuilder.utility.HashMapBuilder;

/**
 * Base implementation for all SQL-Builder. SQL-Builders for specific operations
 * should extend this class.
 * 
 * @author Yanick Lukic, Simon Aebersold
 */
public abstract class SqlBuilderBase implements ISqlBuilder {

	private String[] fields;
	private String[] tables;
	private Map<String, String> values = new HashMap<String, String>();
	private final List<WhereCondition> whereConditions = new ArrayList<WhereCondition>();
	private String orderField;
	private String orderDirection;
	private String groupBy;
	private int limit = 0;
	private int offset = 0;

	protected SqlBuilderBase() {
	}

	@Override
	public SqlBuilderBase fields(String... fields) {
		this.fields = fields;
		return this;
	}

	@Override
	public SqlBuilderBase values(String... values) {
		this.values = HashMapBuilder.build(values);
		return this;
	}

	@Override
	public SqlBuilderBase tables(String... tables) {
		this.tables = tables;
		return this;
	}

	@Override
	public SqlBuilderBase where(String clause) {
		whereConditions.add(new WhereCondition(clause));
		return this;
	}

	@Override
	public SqlBuilderBase where(String field, String clause) {
		whereConditions.add(new WhereCondition(field, clause));
		return this;
	}

	@Override
	public SqlBuilderBase where(String field, String operator, String clause) {
		whereConditions.add(new WhereCondition(field, operator, clause));
		return this;
	}

	@Override
	public SqlBuilderBase order(String orderField, String orderDirection) {
		this.orderField = StringEscapeUtils.escapeJava(orderField);
		this.orderDirection = StringEscapeUtils.escapeJava(orderDirection);
		return this;
	}

	@Override
	public SqlBuilderBase groupBy(String field) {
		this.groupBy = field;
		return this;
	}

	@Override
	public SqlBuilderBase limit(int limit) {
		this.limit = limit;
		return this;
	}

	@Override
	public SqlBuilderBase offset(int offset) {
		this.offset = offset;
		return this;
	}

	@Override
	public abstract SqlQuery build() throws SQLException;

	public String[] getFields() {
		return fields;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public String[] getTables() {
		return tables;
	}

	public List<WhereCondition> getWhereConditions() {
		return whereConditions;
	}

	public String getOrderField() {
		return orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

}
