package com.zdk.demo04;

import com.zdk.demo02.UserService;
import com.zdk.demo02.UserServiceImpl;

import java.lang.reflect.InvocationHandler;

/**
 * @author zdk
 * @date 2021/4/7 19:59
 */
public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色,不存在
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置要代理的对象
        pih.setTarget(userService);
        //动态生成代理类
        UserService proxy = (UserService) pih.getProxy();
        proxy.query();
    }
}
