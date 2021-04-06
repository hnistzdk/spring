package com.zdk.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author zdk
 * @date 2021/4/5 16:35
 */
@Data
public class Student {
    private String name;
    private Address address;
    private String[] book;
    private List<String> hobbies;
    private Map<String, String> card;
    private Set<String>  games;
    private String wife;
    private Properties info;
}
