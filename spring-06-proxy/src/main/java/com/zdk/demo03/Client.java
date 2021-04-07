package com.zdk.demo03;

/**
 * @author zdk
 * @date 2021/4/7 19:48
 */
public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host=new Host();

        //生成代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用以上的代理角色的程序处理来处理我们要调用的接口对象
        pih.setRent(host);

        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }
}
