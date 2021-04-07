package com.zdk.demo02;

/**
 * @author zdk
 * @date 2021/4/7 17:19
 */
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.add();

        //用代理对象去做
        UserServiceProxy proxy =new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.add();
    }
}
