package com.apache.calcite.sql.rule.constant;

import org.apache.calcite.sql.type.SqlTypeName;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanningning on 17/12/13.
 */
public class Constant {

    public static Map<String,SqlTypeName> SQL_TYPE_MAPPING = new HashMap<String, SqlTypeName>();
    public static Map<String, Class> JAVA_TYPE_MAPPING = new HashMap<String, Class>();
    public static Map<String,String> JAVA_SQL_MAPPING = new HashMap<String, String>();
    public static final String DEFAULT_DB_NAME = "rule_bean";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VALUE = "value";

    static {
        SQL_TYPE_MAPPING.put("char", SqlTypeName.CHAR);
        SQL_TYPE_MAPPING.put("varchar", SqlTypeName.VARCHAR);
        SQL_TYPE_MAPPING.put("boolean", SqlTypeName.BOOLEAN);
        SQL_TYPE_MAPPING.put("integer", SqlTypeName.INTEGER);
        SQL_TYPE_MAPPING.put("tinyint", SqlTypeName.TINYINT);
        SQL_TYPE_MAPPING.put("smallint", SqlTypeName.SMALLINT);
        SQL_TYPE_MAPPING.put("bigint", SqlTypeName.BIGINT);
        SQL_TYPE_MAPPING.put("decimal", SqlTypeName.DECIMAL);
        SQL_TYPE_MAPPING.put("numeric", SqlTypeName.DECIMAL);
        SQL_TYPE_MAPPING.put("float", SqlTypeName.FLOAT);
        SQL_TYPE_MAPPING.put("real", SqlTypeName.REAL);
        SQL_TYPE_MAPPING.put("double", SqlTypeName.DOUBLE);
        SQL_TYPE_MAPPING.put("date", SqlTypeName.DATE);
        SQL_TYPE_MAPPING.put("time", SqlTypeName.TIME);
        SQL_TYPE_MAPPING.put("timestamp", SqlTypeName.TIMESTAMP);
        SQL_TYPE_MAPPING.put("any", SqlTypeName.ANY);

        JAVA_TYPE_MAPPING.put("varchar", String.class);
        JAVA_TYPE_MAPPING.put("char", Character.class);
        JAVA_TYPE_MAPPING.put("integer", Integer.class);
        JAVA_TYPE_MAPPING.put("date", Date.class);

        JAVA_SQL_MAPPING.put("java.lang.String","varchar");
        JAVA_SQL_MAPPING.put("java.lang.Integer","integer");
    }
}
