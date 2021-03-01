package com.jing.demo.feignconsumer.service.impl;

import com.jing.demo.feignconsumer.service.IUserConsumerService;
import org.springframework.stereotype.Component;

/**
 * @description: 带降级的service
 * @author: jcwang
 * @create: 2021-03-01 10:30
 **/
@Component
public class UserConsumerServiceWithFallBackImpl implements IUserConsumerService {
    /**
     * 是否可用
     *
     * @return
     */
    @Override
    public String alive() {
        return "fall back";
    }

}
