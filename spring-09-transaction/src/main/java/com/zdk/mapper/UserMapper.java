package com.zdk.mapper;

import com.zdk.pojo.User;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/8 21:00
 */
public interface UserMapper {
    List<User> getUserList();
    int addUser(User user);
    int deleteUser(int id);
}
