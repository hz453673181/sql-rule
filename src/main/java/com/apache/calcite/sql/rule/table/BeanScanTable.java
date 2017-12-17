package com.apache.calcite.sql.rule.table;

import com.apache.calcite.sql.rule.constant.Constant;
import com.apache.calcite.sql.rule.model.BeanTable;
import com.apache.calcite.sql.rule.model.Column;
import org.apache.calcite.DataContext;
import org.apache.calcite.linq4j.AbstractEnumerable;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Enumerator;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.ScannableTable;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.type.SqlTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanningning on 17/12/14.
 * 全表扫描
 */
public class BeanScanTable extends AbstractTable implements ScannableTable{

    private BeanTable beanTable;
    private RelDataType dataType;

    public BeanScanTable(BeanTable beanTable) {
        this.beanTable = beanTable;
    }

    /**
     * 扫描数据
     * */
    public Enumerable<Object[]> scan(DataContext root) {
        List<Column> columnList = beanTable.getColumnList();
        final List<String> types = new ArrayList<String>(columnList.size());
        for(Column column:columnList){
            types.add(column.getType());
        }

        final int [] fields = identityList(this.dataType.getFieldCount());
        return new AbstractEnumerable<Object[]>() {
            public Enumerator<Object[]> enumerator() {
                BeanEnumerator beanEnumerator = new BeanEnumerator(fields,types,beanTable.getDataList());
                return beanEnumerator;
            }
        };
    }

    /**
     * 获取表元数据类型
     * */
    public RelDataType getRowType(RelDataTypeFactory typeFactory) {
        if(dataType == null){
            RelDataTypeFactory.FieldInfoBuilder fieldInfoBuilder = typeFactory.builder();
            for(Column column:beanTable.getColumnList()){
                RelDataType sqlType = typeFactory.createJavaType(Constant.JAVA_TYPE_MAPPING.get(column.getType()));
                sqlType = SqlTypeUtil.addCharsetAndCollation(sqlType,typeFactory);
                fieldInfoBuilder.add(column.getName(),sqlType);
            }
            this.dataType = typeFactory.createStructType(fieldInfoBuilder);
        }
        return this.dataType;
    }


    private static int[] identityList(int n){
        int[] integers = new int[n];
        for(int i=0;i<n;i++){
            integers[i] = i;
        }
        return integers;
    }
}
