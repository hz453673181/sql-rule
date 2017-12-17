package com.apache.calcite.sql.rule.model;

import java.util.List;

/**
 * Created by hanningning on 17/12/13.
 */
public class Database {

    private List<BeanTable> tableList;

    public List<BeanTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<BeanTable> tableList) {
        this.tableList = tableList;
    }
}
