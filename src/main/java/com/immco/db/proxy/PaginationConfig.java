package com.immco.db.proxy;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("rawtypes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationConfig implements Serializable {
    private static final long serialVersionUID = -4329859768972534686L;
    public static final int REFRESH = -1;
    public static final int PAGE_FIRST = 0;
    public static final int PAGE_NEXT = 1;
    public static final int PAGE_PREV = 2;
    public static final int PAGE_LAST = 3;

    private long recCount = 0;
    private int bucketSize = 25;
    private int currentPage = 1;
    private int startRow = 0;
    private int action = PAGE_FIRST;
    private boolean selectAll = false;
    private String sql;
    private String orderBySql;
    private boolean includeColHdrs = false;

    private List resultSet;

    private int[] ignoreCols;

    public String getSql() {
	return sql;
    }

    public void setSql(String sql) {
	this.sql = sql;
    }

    public PaginationConfig() {

    }

    public boolean isSelectAll() {
	return selectAll;
    }

    public void setSelectAll(boolean selectAll) {
	this.selectAll = selectAll;
    }

    public long getRecCount() {
	return recCount;
    }

    public int getBucketSize() {
	return bucketSize;
    }

    public int getCurrentPage() {
	return currentPage;
    }

    public int getAction() {
	return action;
    }

    public void setRecCount(long recCount) {
	this.recCount = recCount;
    }

    public void setBucketSize(int bucketSize) {
	this.bucketSize = bucketSize;
    }

    public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
    }

    public void setAction(int action) {
	this.action = action;
    }

    public int getStartRow() {
	return startRow;
    }

    public void setStartRow(int startRow) {
	this.startRow = startRow;
    }

    public List getResultSet() {
	return resultSet;
    }

    public void setResultSet(List resultSet) {
	this.resultSet = resultSet;
    }

    public String getOrderBySql() {
	return orderBySql;
    }

    public void setOrderBySql(String orderBySql) {
	this.orderBySql = orderBySql;
    }

    public boolean isIncludeColHdrs() {
	return includeColHdrs;
    }

    public void setIncludeColHdrs(boolean includeColHdrs) {
	this.includeColHdrs = includeColHdrs;
    }

    public int[] getIgnoreCols() {
	return ignoreCols;
    }

    public void setIgnoreCols(int... ignoreCols) {
	this.ignoreCols = ignoreCols;
    }
}
