package com.simplify.setup.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Collection;

/** 用于@LinkSign注解的处理
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 23:31
 */
@Component
@Aspect
@Order(1)
public class LinkSignAspect {


    @Autowired
    private LinkResolve linkResolve;

    @Around("@annotation(com.simplify.setup.annotation.LinkSign)")
    public Object processLinkSign(ProceedingJoinPoint pjp) throws Throwable{

        //todo 具体的处理
        // 如果返回结果并非为集合  而是单个实体类 需要考虑重新处理方式
        Object obj = pjp.proceed();
        if(obj instanceof Collection) {
            Collection cols = (Collection) obj;
            linkResolve.setFieldValueForCollection(cols);
        } else {
            //todo 单个实体类
            /*Collection cols = Arrays.asList(obj);
            linkResolve.setFieldValueForCollection(cols);*/
        }

        return obj;
    }
}
