package com.zdk.mapper;

import com.zdk.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * @author zdk
 * @date 2021/4/10 10:39
 */
public class UserMapperImpl implements UserMapper{
    //纯mybatis时的所有操作，都使用sqlSession执行，现在使用SqlSessionTemplate;

    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> getUserList() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserList();
    }
}
