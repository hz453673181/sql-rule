package com.apache.calcite.sql.rule.util;


import java.math.BigDecimal;

/**
 * Created by hanningning on 17/12/14.
 */
public class DataUtil {

    public static Object convertOptiqCellValue(String strValue, String dataType) {
        if (strValue == null)
            return null;

        if ((strValue.equals("") || strValue.equals("\\N")) && !dataType.equals("string"))
            return null;

        // TODO use data type enum instead of string comparison
        if ("date".equals(dataType)) {
            // convert epoch time
        	/*
            Date dateValue = DateFormat.stringToDate(strValue); // NOTE: forces GMT timezone
            long millis = dateValue.getTime();
            long days = millis / (1000 * 3600 * 24);
            return Integer.valueOf((int) days); // Optiq expects Integer instead of Long. by honma
            */
            return DateFormat.stringToDate(strValue);
        } else if ("tinyint".equals(dataType)) {
            return Byte.valueOf(strValue);
        } else if ("short".equals(dataType) || "smallint".equals(dataType)) {
            return Short.valueOf(strValue);
        } else if ("integer".equals(dataType)) {
            return Integer.valueOf(strValue);
        } else if ("long".equals(dataType) || "bigint".equals(dataType)) {
            return Long.valueOf(strValue);
        } else if ("double".equals(dataType)) {
            return Double.valueOf(strValue);
        } else if ("decimal".equals(dataType)) {
            return new BigDecimal(strValue);
        } else if ("timestamp".equals(dataType)) {
            return Long.valueOf(DateFormat.stringToMillis(strValue));
        } else if ("float".equals(dataType)) {
            return Float.valueOf(strValue);
        } else if ("boolean".equals(dataType)) {
            return Boolean.valueOf(strValue);
        } else {
            return strValue;
        }
    }
}
