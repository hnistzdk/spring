package com.demo;


import org.springframework.stereotype.Service;

/**
 * @author zdk
 * @date 2021/12/5 17:46
 */
public interface SomeService {
    void doSome(String name,Integer age);
    Person doOther(String name);
    Person doFirst(String name,Integer age);
}
