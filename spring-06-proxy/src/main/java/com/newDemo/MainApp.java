package com.newDemo;

import com.newDemo.handler.MyInvocationHandler;
import com.newDemo.service.SomeService;
import com.newDemo.service.impl.SomeServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author zdk
 * @date 2021/11/29 19:35
 */
public class MainApp {
    public static void main(String[] args) {
        SomeService target = new SomeServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        SomeService proxyService =(SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        proxyService.doSome();
    }
}
