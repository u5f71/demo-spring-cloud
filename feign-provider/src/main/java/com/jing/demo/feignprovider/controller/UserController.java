package com.jing.demo.feignprovider.controller;

import com.jing.demo.userapi.UserApi;
import com.jing.demo.userapi.bo.Person;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 16:54
 **/

@RestController
public class UserController implements UserApi {
    /**
     * 是否可用
     *
     * @return
     */
    @Override
    public String alive() {
        return "ok";
    }

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @Override
    public Person getById(Integer id) {
        Person p1 = new Person();
        p1.setAge(20);
        p1.setName("stone-" + id);
        p1.setBirthday(new Date());
        return p1;
    }

    /**
     * post
     *
     * @param person
     * @return
     */
    @Override
    public Person postPerson(Person person) {
        person.setName("sys-" + person.getName());
        return person;
    }
}
