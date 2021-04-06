package com.zdk.pojo;

import lombok.Data;

/**
 * @author zdk
 * @date 2021/4/5 14:50
 */
@Data
public class Hello {
    private String name;
    public void show(){
        System.out.println("Hello"+name);
    }
}
