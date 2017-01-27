package com.immco.db.proxy;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("rawtypes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqlResultLoader implements Serializable {

    private static final long serialVersionUID = -7243153081002188949L;
    private String sql;
    private List resultSet;
    private int recordCount;

    public SqlResultLoader() {

    }

    public String getSql() {
	return sql;
    }

    public void setSql(String sql) {
	this.sql = sql;
    }

    public List getResultSet() {
	return resultSet;
    }

    public void setResultSet(List resultSet) {
	this.resultSet = resultSet;
    }

    public int getRecordCount() {
	return recordCount;
    }

    public void setRecordCount(int recordCount) {
	this.recordCount = recordCount;
    }
}
