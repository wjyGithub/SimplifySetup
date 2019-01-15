package com.simplify.setup.support;

import com.simplify.setup.annotation.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

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
                String[] param = link.methodParam();
                if(param.length != 0) {
                    //todo 待处理 参数字段非空
                }
            }
        }


    }




}
