package com.newDemo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author zdk
 * @date 2021/11/29 19:32
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行时间-"+ new Date());
        Object invoke = method.invoke(target, args);
        System.out.println("执行完毕,提交事务");
        return invoke;
    }
}
