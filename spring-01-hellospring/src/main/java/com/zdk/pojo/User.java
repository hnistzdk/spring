package com.zdk.pojo;

import lombok.Data;

/**
 * @author zdk
 * @date 2021/4/5 15:51
 */

public class User {
    private String name;
    public User(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
