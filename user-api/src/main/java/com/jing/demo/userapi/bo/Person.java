package com.jing.demo.userapi.bo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 14:43
 **/
@Data
public class Person {
    private String name;
    private int age;
    private Date birthday;
}
