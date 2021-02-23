package com.jing.demo.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-23 16:14
 **/
@RestController
@Slf4j
public class ConsumerController {

    /**
     * spring cloud的接口
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/service-list")
    public String listService() {
        // 获取注册中心中的services.
        final List<String> services = discoveryClient.getServices();
        // services = [provider, consumer]
        // 对应![](https://tva1.sinaimg.cn/large/008eGmZEgy1gnxjmdktagj31g20u0n8j.jpg)
        log.info("services = {}", services);
        return services.toString();
    }

    @GetMapping("/client/sayHi")
    public String sayHi() {
        String result = "";
        // 调用服务端的接口
        final List<InstanceInfo> providerList = eurekaClient.getInstancesByVipAddress("provider", false);
        // 从服务列表中获取某个服务
        final InstanceInfo instanceInfo = providerList.get(0);
        if (InstanceInfo.InstanceStatus.UP.equals(instanceInfo.getStatus())) {
            String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/getHi";
            RestTemplate restTemplate = new RestTemplate();
            result = restTemplate.getForObject(url, String.class);

        }
        return result;
    }

    @GetMapping("/client/ribbon/sayHi")
    public String ribbonSayHi() {
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        final ServiceInstance provider = loadBalancerClient.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/getHi";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
