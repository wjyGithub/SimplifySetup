package com.simplify.setup.util;

import com.simplify.setup.Exception.IllegalFieldException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供对实体类的操作工具类
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/18 23:44
 */

public class BeanUtil {

    private BeanUtil(){}

    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

    private static final String ILLEGAL_FIELD_MESSAGE = "字段名不在该指定的类中";

    /**
     * 用于获取某一个实体类下,指定属性名的class类型
     * @param bean 目标class,即实体类的class
     * @param fieldNames class里面所对应成员属性名
     * @return 成员属性名的class,数组中的顺序和fieldNames数组中的顺序相对应
     */
    public static final Class<?>[] getClassType(Class<?> bean,String[] fieldNames) {

        if(fieldNames == null || fieldNames.length == 0) {
            return new Class<?>[0];
        }

        List<Class<?>> fieldClasses = new ArrayList<>();

        for(String fieldName : fieldNames) {
            try {
                Class<?> type = bean.getDeclaredField(fieldName).getType();
                fieldClasses.add(type);
            } catch (NoSuchFieldException e) {
                log.error("getClassType:{}",e);
                throw new IllegalFieldException(ILLEGAL_FIELD_MESSAGE);
            }
        }

        return fieldClasses.toArray(new Class<?>[fieldClasses.size()]);
    }


    /**
     * 获取某一对象中,指定的字段名的数值
     * @param targetBean 目标对象
     * @param fieldNames 获取的字段名
     * @return 指定的字段名的数值,数值顺序和给定的字段名数组顺序一直
     */
    public static final Object[] getFieldValue(Object targetBean,String[] fieldNames)  {

        Class<?> clazz = targetBean.getClass();

        List<Object> fieldValues = new ArrayList<>();

        for(String fieldName : fieldNames) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(targetBean);
                fieldValues.add(value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("getFieldValue:{}",e);
                throw new IllegalFieldException(ILLEGAL_FIELD_MESSAGE);
            }

        }
        return fieldValues.toArray(new Object[fieldValues.size()]);
    }
}
