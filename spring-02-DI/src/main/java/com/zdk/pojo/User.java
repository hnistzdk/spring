package com.zdk.pojo;

import lombok.Data;

/**
 * @author zdk
 * @date 2021/4/5 18:42
 */
@Data
public class User {
    private  String name;
    private int age;
    public User() {
    }

    public User(String name,int age) {
        this.name = name;
        this.age=age;
    }
}
