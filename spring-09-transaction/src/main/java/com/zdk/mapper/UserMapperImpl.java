package com.zdk.mapper;

import com.zdk.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/10 10:39
 */
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{
    @Override
    public List<User> getUserList() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        User user=new User(6, "张振明","11111111");
        mapper.addUser(user);
        mapper.deleteUser(6);
        return mapper.getUserList();
    }

    @Override
    public int addUser(User user) {

        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
