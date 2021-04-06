package com.zdk.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

/**
 * @author zdk
 * @date 2021/4/5 19:40
 */
@Data
public class People {
    //可以在属性上使用此注解 或者在set方法上
    //使用注解后可以忽略set方法 即无set方法也可完成注入

    //如果显式定义了autowired属性的required参数为false  则说明该属性可以为空
    /**
     * 如果@Autowired 自动装配的环境比较复杂，自动装配无法通过一个注解@Autowired来完成的时候，我们可以
     * 使用@Qualifier(value = "xxx")去配合@Autowired去使用，指定一个唯一的bean进行注入
     */

    //@Resource注解也可以实现自动装配,不需要spring的包，不过没有spring的提示
    //它会根据先根据type再根据name去自动寻找进行装配(与@Autowired相同)，且也可以指定name去指定唯一的bean注入   用处更广

    @Autowired(required = false)
    @Qualifier(value = "cat000")
    @Resource
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;
}
