package org.luvx.cloud.feign.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.luvx.cloud.feign.client.UserFeignClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {
    @Resource
    private DiscoveryClient    discoveryClient;
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate       restTemplate;
    @Resource
    private UserFeignClient    userFeignClient;

    @GetMapping(value = "/product/user/{name}")
    public String getUserByName(@PathVariable String name) {
        String serviceId = "user-service";

        // 1
        // List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
        // int index = RandomUtils.nextInt(instanceList.size());
        // ServiceInstance serviceInstance = instanceList.get(index);

        // 2
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);

        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + name;

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/product/user/{name}/feign")
    public String getUserByNameFromFeign(@PathVariable String name) {
        return userFeignClient.getByName(name);
    }
}
