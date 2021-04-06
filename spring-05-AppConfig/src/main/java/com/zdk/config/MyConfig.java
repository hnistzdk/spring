package com.zdk.config;

import com.zdk.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zdk
 * @date 2021/4/6 20:02
 */

//其实这里也可以不加@Configuration注解也可以成功
//@Import注解可以将别的config类引入到使用到此注解的类

@Configuration
@ComponentScan("com.zdk")
@Import(MyConfig2.class)
public class MyConfig {

    //以下加注解的方法相当于xml配置中bean标签，方法的名字就是bean的id，方法的返回值就是class

    @Bean
    public User myUser(){
        return new User();
    }
}
