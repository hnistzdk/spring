package com.zdk.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author zdk
 * @date 2021/4/8 20:19
 */
//使用注解方式实现AOP

//使用注解标注这个类是一个切面

@Aspect
public class AnnotationPointCut {
    @Before("execution(* com.zdk.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("========方法执行前========");
    }
    @After("execution(* com.zdk.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("========方法执行后========");
    }

    //在环绕增强中，可以给定一个参数，代表我们要获取处理切入的点

    @Around("execution(* com.zdk.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        System.out.println("方法签名(名称)Signature:"+joinPoint.getSignature());
        //执行around方法
        Object proceed = joinPoint.proceed();
        System.out.println("环绕后");
    }
}
