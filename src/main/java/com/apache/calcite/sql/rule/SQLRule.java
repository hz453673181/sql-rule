package com.apache.calcite.sql.rule;

import com.apache.calcite.sql.rule.constant.Constant;
import com.apache.calcite.sql.rule.model.BeanTable;
import com.apache.calcite.sql.rule.model.Column;
import com.apache.calcite.sql.rule.model.Database;
import com.apache.calcite.sql.rule.model.Databases;
import com.apache.calcite.sql.rule.pool.ConnectionProviderImpl;
import com.apache.calcite.sql.rule.util.BeanUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanningning on 17/12/15.
 */
public class SQLRule {

    private static final SQLRule sqlRule = new SQLRule();
    private Database database;
    private final List<BeanTable> tableList = new ArrayList<BeanTable>();
    private Object object;
    private Connection connection;


    public static SQLRule getInstance(){
        return sqlRule;
    }

    private SQLRule() {
        ConnectionProviderImpl connectionProvider = new ConnectionProviderImpl();
        this.connection = connectionProvider.getConnection();

        database = new Database();
    }

    /**
     * 添加表
     * */
    public void addTable(Object object){
        Map<String, Map<String, Object>> beanMap = BeanUtil.reflexBean(object);

        List<Column> columnList = new ArrayList<Column>();
        List<List<String>> dataList = new ArrayList<List<String>>();
        List<String> row = new ArrayList<String>();

        for(Map.Entry<String,Map<String,Object>> entry:beanMap.entrySet()){
            String key = entry.getKey();
            Map<String,Object> value = entry.getValue();
            String fieldType = value.get(Constant.KEY_TYPE).toString();
            String fieldValue = value.get(Constant.KEY_VALUE).toString();

            columnList.add(new Column(key.toUpperCase(), Constant.JAVA_SQL_MAPPING.get(fieldType)));

            row.add(fieldValue);
        }
        dataList.add(row);

        BeanTable beanTable = new BeanTable();
        beanTable.setTableName(object.getClass().getSimpleName().toUpperCase());
        beanTable.setColumnList(columnList);
        beanTable.setDataList(dataList);

        tableList.add(beanTable);
        database.setTableList(tableList);
        Databases.databaseMap.put(Constant.DEFAULT_DB_NAME,database);
    }

    /**
     * 执行规则
     * */
    public Map<String,Object> execute(String ruleSql){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ruleSql.toUpperCase());
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()){
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    map.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
