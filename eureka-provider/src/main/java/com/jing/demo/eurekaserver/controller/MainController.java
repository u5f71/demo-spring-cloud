package com.jing.demo.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
