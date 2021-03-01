package com.jing.demo.feignconsumer.service;

import com.jing.demo.feignconsumer.service.impl.UserConsumerServiceWithFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 实现了api接口的类
 * <p>
 * 不通过eureka时, 可以通过指定url来直接调用服务端
 * @FeignClient(name = "user-provider1", url = "http://localhost:91/")
 * @author: jcwang
 * @create: 2021-02-24 17:10
 **/
@FeignClient(name = "user-provider", fallback = UserConsumerServiceWithFallBackImpl.class)
public interface IUserConsumerService {
    /**
     * 是否可用
     *
     * @return
     */
    @GetMapping("/User/alive")
    String alive();
}
