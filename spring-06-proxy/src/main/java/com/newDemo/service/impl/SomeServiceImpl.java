package com.newDemo.service.impl;

import com.newDemo.service.SomeService;

/**
 * @author zdk
 * @date 2021/11/29 19:31
 */
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("执行doSome()");
    }

    @Override
    public void doOther() {
        System.out.println("执行doOther()");
    }
}
