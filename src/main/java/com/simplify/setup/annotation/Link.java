package com.simplify.setup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  用于指定关联的目标字段
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 22:35
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Link {

    /**
     * 目标的bean
     * @return
     */
    Class<?> targetBean();

    /**
     * 目标方法名
     */
    String methodName();

    /**
     * 目标方法的参数列表(和当前实体类中的成员属性对应)
     * @return
     */
    String[] methodParam() default {};

    /**
     * 关联的目标字段
     * 如果为默认值"",即直接将结果集赋值给该字段
     * @return
     */
    String targetField() default "";
}
