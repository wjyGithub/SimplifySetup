package com.simplify.setup.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 用于@LinkSign注解的处理
 * @author jianyuan.wei@hand-china.com
 * @date 2019/1/14 23:31
 */
@Component
@Aspect
public class LinkSignAspect {


    @Autowired
    private LinkResolve linkResolve;

    @Around("@annotation(com.simplify.setup.annotation.LinkSign)")
    public Object processLinkSign(ProceedingJoinPoint pjp) throws Throwable{

        //todo 具体的处理


        return null;
    }
}
