package com.apache.calcite.sql.rule.table;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.util.Map;

/**
 * Created by hanningning on 17/12/13.
 */
public class BeanSchemaFactory implements SchemaFactory{


    public Schema create(SchemaPlus parentSchema, String name, Map<String, Object> operand) {
        return new BeanSchema(name);
    }
}
