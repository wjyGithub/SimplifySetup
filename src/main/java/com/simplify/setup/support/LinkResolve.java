package com.simplify.setup.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 注解@Link解析器
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 23:25
 */
@Component
public class LinkResolve implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    //todo 用于处理解析 参数待定
    public void parse(){

    }
}
