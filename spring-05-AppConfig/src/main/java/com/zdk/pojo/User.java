package com.zdk.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zdk
 * @date 2021/4/6 19:09
 */
@Component
public class User {
    @Value("张迪凯")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
