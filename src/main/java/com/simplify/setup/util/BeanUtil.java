package com.simplify.setup.util;

import com.simplify.setup.Exception.IllegalFieldException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 提供对实体类的操作工具类
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/18 23:44
 */
public class BeanUtil {

    private BeanUtil(){}

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
                throw new IllegalFieldException("字段名不在该指定的类中");
            }
        }

        return fieldClasses.toArray(new Class<?>[fieldClasses.size()]);
    }
}
