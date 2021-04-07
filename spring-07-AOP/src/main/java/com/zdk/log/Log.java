package com.zdk.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zdk
 * @date 2021/4/7 20:40
 */
public class Log implements MethodBeforeAdvice {

    //method：是要执行的目标对象的方法
    //objects：是参数(args)
    //o：目标对象(target)

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"方法被执行了");
    }
}
