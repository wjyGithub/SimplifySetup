package com.simplify.setup.annotation;

/**
 *  用于指定关联的目标字段
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 22:35
 */
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
     * 目标方法的参数列表
     * @return
     */
    String[] methodParam();

    /**
     * 关联的目标字段
     * @return
     */
    String targetField();
}
