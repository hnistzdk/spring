package com.zdk.diy;

/**
 * @author zdk
 * @date 2021/4/8 19:59
 */
//使用自定义类实现AOP

public class DiyPointCut {
    public void before(){
        System.out.println("========方法执行前========");
    }
    public void after(){
        System.out.println("========方法执行后========");
    }
}
