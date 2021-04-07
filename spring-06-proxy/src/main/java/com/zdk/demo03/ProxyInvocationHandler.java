package com.zdk.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zdk
 * @date 2021/4/7 19:42
 */

//用此类自动生成代理类

public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Rent rent;
    public void setRent(Rent rent) {
        this.rent = rent;
    }
    //生成得到代理类

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(),this );
    }

    //处理代理实例，并返回结果

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //在调用invoke方法之前调用此方法，此方法在Client类中的proxy.rent()执行的时候也会被执行
        seeHouse();

        //动态代理的本质，就是使用反射机制实现
        Object result = method.invoke(rent, args);
        //收中介费应当在完成租房这个动作之后进行，所以在invoke方法执行之后执行
        money();
        return result;
    }

    public void seeHouse(){
        System.out.println("中介带看房子");
    }
    public void money(){
        System.out.println("收中介费");
    }
}
