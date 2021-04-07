package com.zdk.demo01;

/**
 * @author zdk
 * @date 2021/4/7 16:58
 */
public class Client {
    public static void main(String[] args) {
        Host host=new Host();
        //代理,中介帮房东出租房子,但是中介(代理角色)能执行一些其他附属操作
        Proxy proxy = new Proxy(host);
        //通过代理实现,不用找房东,直接找中介租房
        proxy.rent();
    }
}
