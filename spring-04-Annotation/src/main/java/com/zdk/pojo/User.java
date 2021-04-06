package com.zdk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zdk
 * @date 2021/4/6 19:09
 */
//@Component注解(义为组成部分、组件)等价于<bean id="user" class="com.zdk.pojo.User"/>

@Component
public class User {
    @Value("使用注解注入的值:张迪凯")
    public String name;
    @Value("使用注解注入的值:张迪凯")
    public void setName(String name) {
        this.name = name;
    }
}
