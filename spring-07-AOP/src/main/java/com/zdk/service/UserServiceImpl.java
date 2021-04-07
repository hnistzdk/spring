package com.zdk.service;

/**
 * @author zdk
 * @date 2021/4/7 20:38
 */
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }
    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }
    @Override
    public void modify() {
        System.out.println("修改了一个用户");
    }
    @Override
    public void query() {
        System.out.println("查询了一个用户");
    }
}
