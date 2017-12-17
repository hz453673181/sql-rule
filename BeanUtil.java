package com.apache.calcite.sql.rule.util;

import com.apache.calcite.sql.rule.constant.Constant;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanningning on 17/12/14.
 */
public class BeanUtil {

    /**
     * 反射Bean获取属性，类型，值
     * */
    public static Map<String,Map<String,Object>> reflexBean(Object obj) {
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Class clazz = obj.getClass();
        Field[] fieldList = clazz.getDeclaredFields();
        Map<String,String> fieldMethodMap = new HashMap<String, String>();
        Map<String,Map<String,Object>> result = new HashMap<String, Map<String, Object>>();
        for(int i=0;i<fieldList.length;i++){
            Field field = fieldList[i];
            String fieldName = field.getName();
            String fieldType = field.getType().getName();
            Map<String,Object> map = new HashMap<String,Object>();

            map.put(Constant.KEY_NAME,fieldName);
            map.put(Constant.KEY_TYPE,fieldType);
            result.put(fieldName,map);
            String getMethodName = "get" + toUpperFristChar(fieldName);

            fieldMethodMap.put(getMethodName,fieldName);
        }

        Method[] methods = clazz.getMethods();
        for(int i=0;i<methods.length;i++){
            Method method = methods[i];
            if(fieldMethodMap.containsKey(method.getName())){
                String fieldName = fieldMethodMap.get(method.getName());
                try {
                    result.get(fieldName).put(Constant.KEY_VALUE,method.invoke(obj));
                } catch (Exception e) {
                    result.get(fieldName).put(Constant.KEY_VALUE,null);
                }
            }
        }

        return result;
    }

    public static String toUpperFristChar(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

}
