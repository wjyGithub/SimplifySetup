package com.simplify.setup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  用于标识Link注解的触发
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 22:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkSign {
}
