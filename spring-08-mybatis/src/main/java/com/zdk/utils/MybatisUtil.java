package com.zdk.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zdk
 * @date 2021/4/9 18:03
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSession getSqlSession() throws IOException {
        String resource="mybatis-config.xml";
        InputStream in =Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
        //设置为自动提交事务
        return sqlSessionFactory.openSession(true);
    }
}
