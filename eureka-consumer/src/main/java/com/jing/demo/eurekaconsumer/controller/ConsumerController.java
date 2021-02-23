package com.jing.demo.eurekaconsumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-23 16:14
 **/
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/service-list")
    public String listService() {
        // 获取注册中心中的services.
        final List<String> services = discoveryClient.getServices();
        // services = [provider, consumer]
        // 对应![](https://tva1.sinaimg.cn/large/008eGmZEgy1gnxjmdktagj31g20u0n8j.jpg)
        log.info("services = {}", services);
        return "Hi";
    }
}
