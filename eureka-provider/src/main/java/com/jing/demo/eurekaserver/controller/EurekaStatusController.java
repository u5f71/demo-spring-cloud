package com.jing.demo.eurekaserver.controller;

import com.jing.demo.eurekaserver.service.impl.HealthStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 10:24
 **/
@RestController
public class EurekaStatusController {

    @Autowired
    private HealthStatusServiceImpl healthStatusService;

    @GetMapping("/health")
    public Object setHealth(
        @RequestParam("status")
            Boolean status) {
        healthStatusService.setUp(status);
        return healthStatusService.getHealth(false);
    }
}
