package com.jing.demo.feignprovider.controller;

import com.jing.demo.userapi.UserApi;
import com.jing.demo.userapi.bo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 16:54
 **/
@Slf4j
@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    private String port;

    /**
     * 请求次数
     */
    private final AtomicInteger count = new AtomicInteger();

    /**
     * 是否可用
     *
     * @return
     */
    @Override
    public String alive() {
        try {
            int i = count.getAndIncrement();
            log.info("count = {}, port = {}", i, port);
            log.info("start sleeping");
            Thread.sleep(0);
            log.info("end sleeping");

        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

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
