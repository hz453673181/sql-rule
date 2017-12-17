package com.apache.calcite.sql.rule.table;

import com.apache.calcite.sql.rule.util.DataUtil;
import org.apache.calcite.linq4j.Enumerator;

import java.util.List;

/**
 * Created by hanningning on 17/12/14.
 */
public class BeanEnumerator<E> implements Enumerator<E> {

    private List<List<String>> dataList = null;
    private int currentIndex = -1;
    private RowConverter<E> rowConvert;
    private List<String> columnTypes;

    /**
     * @param fields 字段索引数组
     * @param types 字段类型列表
     * @param dataList 数据集合
     * */
    public BeanEnumerator(int[] fields, List<String> types, List<List<String>> dataList) {
        this.dataList = dataList;
        this.columnTypes = types;
        this.rowConvert = (RowConverter<E>) new ArrayRowConverter(fields);
    }

    public E current() {
        List<String> line = dataList.get(currentIndex);
        return rowConvert.convertRow(line, this.columnTypes);
    }

    public boolean moveNext() {
        return ++ currentIndex < dataList.size();
    }

    public void reset() {

    }

    public void close() {

    }

    abstract static class RowConverter<E>{
        abstract E convertRow(List<String> rows, List<String> columnTypes);
    }

    static class ArrayRowConverter extends RowConverter<Object[]> {
        private int[] fields;

        public ArrayRowConverter(int[] fields) {
            this.fields = fields;
        }

        @Override
        Object[] convertRow(List<String> rows, List<String> columnTypes) {
            Object[] objects = new Object[fields.length];
            int i = 0 ;
            for(int field : this.fields) {
                objects[i++] = DataUtil.convertOptiqCellValue(rows.get(field), columnTypes.get(field));
            }
            return objects;
        }
    }
}
