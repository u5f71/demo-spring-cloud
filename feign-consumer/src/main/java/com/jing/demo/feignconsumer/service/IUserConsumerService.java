package com.jing.demo.feignconsumer.service;

import com.jing.demo.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description: 实现了api接口的类
 *
 * 不通过eureka时, 可以通过指定url来直接调用服务端
 * @FeignClient(name = "user-provider1", url = "http://localhost:91/")
 *
 * @author: jcwang
 * @create: 2021-02-24 17:10
 **/
@FeignClient(name = "user-provider")
public interface IUserConsumerService extends UserApi {
}
