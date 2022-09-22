package com.xh.sample.springcloud.product.feign;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping(value = "/product/user/{name}")
    public String getUserByName(@PathVariable String name) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("user-service");
        int index = RandomUtils.nextInt(instanceList.size());
        ServiceInstance serviceInstance = instanceList.get(index);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + name;

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/product/user/{name}/feign")
    public String getUserByNameFromFeign(@PathVariable String name) {
        return userFeignClient.getByName(name);
    }
}
