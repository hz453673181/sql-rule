package com.apache.calcite.sql.rule.model;

import java.util.List;

/**
 * Created by hanningning on 17/12/13.
 */
public class BeanTable {

    private String tableName;
    private List<Column> columnList;
    private List<List<String>> dataList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public List<List<String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<String>> dataList) {
        this.dataList = dataList;
    }
}
