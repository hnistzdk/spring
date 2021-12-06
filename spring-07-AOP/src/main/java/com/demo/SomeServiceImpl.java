package com.demo;

import org.springframework.stereotype.Service;

/**
 * @author zdk
 * @date 2021/12/5 17:46
 */
@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name,Integer age) {
        System.out.println("--------执行目标方法---------");
    }

    @Override
    public Person doOther(String name) {
        System.out.println("--------执行doOther(String name)方法---------");
        return new Person(name, 18);
    }

    @Override
    public Person doFirst(String name, Integer age) {
        System.out.println("执行doFirst(String name, Integer age)方法");
        return new Person(name, age);
    }
}
