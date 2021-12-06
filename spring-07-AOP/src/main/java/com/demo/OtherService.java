package com.demo;

import org.springframework.stereotype.Service;

/**
 * @author zdk
 * @date 2021/12/6 19:38
 */
@Service
public class OtherService {
    public void doSome(String name,Integer age) {
        System.out.println("--------执行目标方法---------");
    }
}
