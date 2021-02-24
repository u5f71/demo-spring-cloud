package com.jing.demo.eurekaconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.jing.demo.eurekaconsumer.bo.Person;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/service-list")
    public String listService() {
        // 获取注册中心中的services.
        final List<String> services = discoveryClient.getServices();
        // services = [provider, consumer]
        // 对应![](https://tva1.sinaimg.cn/large/008eGmZEgy1gnxjmdktagj31g20u0n8j.jpg)
        log.info("services = {}", services);
        return services.toString();
    }

    @GetMapping("/service-instance-list")
    public Object listServiceInstance() {
        // 对应的截图
        // ![](https://tva1.sinaimg.cn/large/008eGmZEgy1gnxlnwlx09j31c70u0ds9.jpg)
        Map<String, List<ServiceInstance>> result = new HashMap<>(4);
        final List<String> services = discoveryClient.getServices();
        for (String serviceId : services) {
            final List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            result.put(serviceId, instances);
        }
        return JSON.toJSON(result);
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
            RestTemplate restTemplate1 = new RestTemplate();
            result = restTemplate1.getForObject(url, String.class);

        }
        return result;
    }

    @GetMapping("/client/ribbon/sayHi")
    public String ribbonSayHi() {
        String url = "http://provider/getHi";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/client/getStudent")
    public ResponseEntity<Person> getStudent() {
        String url = "http://provider/getStudent";
        return restTemplate.getForEntity(url, Person.class);
    }
}
