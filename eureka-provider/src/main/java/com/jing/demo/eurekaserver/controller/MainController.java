package com.jing.demo.eurekaserver.controller;

import com.jing.demo.eurekaserver.bo.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description: 对外提供服务
 * @author: jcwang
 * @create: 2021-02-23 14:23
 **/

@RestController
public class MainController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getHi")
    public String getHi() {
        return "Hi! My port is " + port;
    }

    @GetMapping("/getStudent")
    public Person getStudent() {
        Person p = new Person();
        p.setAge(20);
        p.setName("stone");
        p.setBirthday(new Date());
        return p;
    }

}
