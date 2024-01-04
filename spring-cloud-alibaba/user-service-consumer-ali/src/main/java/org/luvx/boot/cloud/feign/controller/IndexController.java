package org.luvx.boot.cloud.feign.controller;

import static org.luvx.boot.cloud.feign.consts.ServiceHolder.USER_SERVICE;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate       restTemplate;
    @Resource
    @Qualifier("restTemplateLoadBalanced")
    private RestTemplate       restTemplateLoadBalanced;

    @GetMapping(value = "/product/user/{name}")
    public String getUserByName(@PathVariable String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(USER_SERVICE);

        String url = STR."http://\{serviceInstance.getHost()}:\{serviceInstance.getPort()}/user/\{name}";

        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(value = "/ribbon/user/{name}")
    public String getUserByName1(@PathVariable String name) {
        String url = STR."http://\{USER_SERVICE}/user/\{name}";
        return restTemplateLoadBalanced.getForObject(url, String.class);
    }
}
