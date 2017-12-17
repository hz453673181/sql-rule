package com.apache.calcite.sql.rule.table;

import com.apache.calcite.sql.rule.model.BeanTable;
import com.apache.calcite.sql.rule.model.Database;
import com.apache.calcite.sql.rule.model.Databases;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanningning on 17/12/13.
 */
public class BeanSchema extends AbstractSchema{

    /**
     * database schema name
     * */
    private String dbName;

    public BeanSchema(String dbName) {
        this.dbName = dbName;
    }

    /**
     * 获取表名
     * */
    @Override
    protected Map<String, Table> getTableMap() {
        Map<String,Table> tables = new HashMap<String, Table>();
        Database database = Databases.databaseMap.get(dbName);
        if(database != null){
            List<BeanTable> list = database.getTableList();
            for(BeanTable beanTable:list){
                tables.put(beanTable.getTableName(),new BeanScanTable(beanTable));
            }
        }
        return tables;
    }
}
