package com.simplify.setup.support;

import com.simplify.setup.annotation.Link;
import com.simplify.setup.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * 注解@Link解析器
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 23:25
 */
@Component
public class LinkResolve implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(LinkResolve.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    //todo 用于处理解析 参数待定
    //todo 考虑下如何通过缓存优化代码性能
    public void setFieldValueForCollection(Collection cols){

        if(cols == null || cols.isEmpty()) {
            return;
        }
        //获取集合中存储对象的Class类型
        Class<?> cls = cols.iterator().next().getClass();

        //获取所有的存储对象类的字段
        Field[] fields = cls.getDeclaredFields();

        for(Field field : fields) {
            Link link = field.getAnnotation(Link.class);
            if(link != null) {
                field.setAccessible(true);
                //获取目标bean的Class类型
                Class<?> beanClass = link.targetBean();
                //取出bean的实例对象
                Object bean = applicationContext.getBean(beanClass);
                if(bean == null) {
                    log.warn("{}没有对应的实例对象",beanClass.getName());
                    return;
                }
                //todo 目前方法的参数都来至于实体类中的成员属性
                String[] param = link.methodParam();
                if(param.length != 0) {
                    //todo 待处理 参数字段非空
                    try {
                        Class<?>[] fieldClasses = BeanUtil.getClassType(cls,param);
                        //获取目前实体中,指定的方法
                        Method method = beanClass.getMethod(link.methodName(), fieldClasses);

                        //从实体类中，获取参数的具体数据
                        for(Object obj : cols) {
                            Object[] args = BeanUtil.getFieldValue(obj, param);
                            //todo 需要考虑结果集是否为List
                            Object result = method.invoke(bean, args);

                            //targetField是空的,且结果集为List  表示需要将结果集设置进去
                            if(result != null && StringUtils.isEmpty(link.targetField()) && result instanceof List) {
                                field.set(obj,result);
                            }
                            //非空 说明返回的结果集并不是集合,而是某一个实体类
                            if(result != null && !StringUtils.isEmpty(link.targetField())) {

                                Field resultField = result.getClass().getDeclaredField(link.targetField());
                                resultField.setAccessible(true);
                                Object value = resultField.get(result);
                                field.set(obj,value);
                            }
                        }

                    } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                       log.error("解析出错:",e);
                    }
                }
            }
        }


    }

}