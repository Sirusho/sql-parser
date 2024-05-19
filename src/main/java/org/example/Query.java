package org.example;

import java.util.ArrayList;
import java.util.List;

class Query {
    private List<String> columns = new ArrayList<>();
    private List<String> fromSources = new ArrayList<>();
    private List<Join> joins = new ArrayList<>();
    private List<String> whereClauses = new ArrayList<>();
    private List<String> groupByColumns = new ArrayList<>();
    private List<String> sortColumns = new ArrayList<>();
    private Integer limit;
    private Integer offset;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getFromSources() {
        return fromSources;
    }

    public void setFromSources(List<String> fromSources) {
        this.fromSources = fromSources;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public List<String> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(List<String> whereClauses) {
        this.whereClauses = whereClauses;
    }

    public List<String> getGroupByColumns() {
        return groupByColumns;
    }

    public void setGroupByColumns(List<String> groupByColumns) {
        this.groupByColumns = groupByColumns;
    }

    public List<String> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<String> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public void addJoin(Join join) {
        joins.add(join);
    }

}

