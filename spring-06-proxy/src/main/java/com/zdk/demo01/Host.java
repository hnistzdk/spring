package com.zdk.demo01;

/**
 * @author zdk
 * @date 2021/4/7 16:58
 */
public class Host implements Rent{
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
