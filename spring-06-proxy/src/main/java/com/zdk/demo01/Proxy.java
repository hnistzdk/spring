package com.zdk.demo01;

/**
 * @author zdk
 * @date 2021/4/7 16:59
 */
//代理类(代理角色)

public class Proxy {
    private Host host;
    public Proxy() {
    }
    public Proxy(Host host) {
        this.host = host;
    }
    public void rent(){
        host.rent();
    }
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    public void heTong(){
        System.out.println("中介和你签租赁合同");
    }
    public void money(){
        System.out.println("中介收中介费");
    }
}
