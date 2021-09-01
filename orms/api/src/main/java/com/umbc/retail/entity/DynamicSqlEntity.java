package com.umbc.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="R_DYNAMIC_SQL")
public class DynamicSqlEntity {

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SQL_ID")
	private Integer sqlId;

	@Column(name="NUMBER_OF_PARAMETERS")
	private Integer numberOfParameters;
	
	@Column(name="SQL_COLUMNS")
	private String sqlColumns;
	
	@Column(name="SQL_STATEMENT")
	private String sqlStatement;
	
	public DynamicSqlEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DynamicSqlEntity(Integer sqlId, Integer numberOfParameters, String sqlColumns, String sqlStatement) {
		super();
		this.sqlId = sqlId;
		this.numberOfParameters = numberOfParameters;
		this.sqlColumns = sqlColumns;
		this.sqlStatement = sqlStatement;
	}

	public Integer getSqlId() {
		return sqlId;
	}

	public void setSqlId(Integer sqlId) {
		this.sqlId = sqlId;
	}

	public Integer getNumberOfParameters() {
		return numberOfParameters;
	}

	public void setNumberOfParameters(Integer numberOfParameters) {
		this.numberOfParameters = numberOfParameters;
	}

	public String getSqlColumns() {
		return sqlColumns;
	}

	public void setSqlColumns(String sqlColumns) {
		this.sqlColumns = sqlColumns;
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	
	
	

	
}
