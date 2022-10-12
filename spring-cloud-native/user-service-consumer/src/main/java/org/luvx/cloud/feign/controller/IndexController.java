package org.luvx.cloud.feign.controller;

import static org.luvx.cloud.feign.consts.ServiceHolder.USER_SERVICE;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("restTemplateLoadBalanced")
    private RestTemplate       restTemplateLoadBalanced;
    @Resource
    private UserFeignClient    userFeignClient;

    @GetMapping(value = "/product/user/{name}")
    public String getUserByName(@PathVariable String name) {
        // 1
        // List<ServiceInstance> instanceList = discoveryClient.getInstances(serviceId);
        // int index = RandomUtils.nextInt(instanceList.size());
        // ServiceInstance serviceInstance = instanceList.get(index);

        // 2
        ServiceInstance serviceInstance = loadBalancerClient.choose(USER_SERVICE);

        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + name;

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/ribbon/user/{name}")
    public String getUserByName1(@PathVariable String name) {
        String serviceId = "user-service";
        return restTemplateLoadBalanced.getForObject("http://" + serviceId + "/user/" + name, String.class);
    }

    @GetMapping(value = "/product/user/{name}/feign")
    public String getUserByNameFromFeign(@PathVariable String name) {
        return userFeignClient.getByName(name);
    }
}
