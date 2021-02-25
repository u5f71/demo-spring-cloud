package com.jing.demo.feignconsumer.controller;

import com.jing.demo.feignconsumer.service.IUserConsumerService;
import com.jing.demo.userapi.bo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 17:04
 **/
@RestController
public class UserController {

    @Autowired
    private IUserConsumerService userConsumerService;

    @GetMapping("/client/alive")
    public String alive() {
        return userConsumerService.alive();
    }

    @GetMapping("/client/getById")
    public Person getById(
        @RequestParam("id")
            Integer id) {
        return userConsumerService.getById(id);
    }
}
