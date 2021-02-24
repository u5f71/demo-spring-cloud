package com.jing.demo.eurekaserver.service.impl;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 10:25
 **/
@Service
public class HealthStatusServiceImpl implements HealthIndicator {

    private Boolean isUp = true;
    private Health health;

    public void setUp(Boolean up) {
        isUp = up;
    }

    @Override
    public Health health() {
        if (isUp) {
            health = new Health.Builder().up().build();
        } else {
            health = new Health.Builder().down().build();
        }
        return health;
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return health;
    }
}
