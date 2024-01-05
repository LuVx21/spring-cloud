package org.luvx.boot.cloud.feign.controller;

import static org.luvx.boot.cloud.feign.consts.ServiceHolder.USER_SERVICE;

import javax.annotation.Resource;

import org.luvx.boot.cloud.feign.service.UserServiceClient;
import org.luvx.boot.web.response.R;
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
    @Resource
    private UserServiceClient  userServiceClient;

    @GetMapping(value = "/user/v1/{name}")
    public Object getUserByName(@PathVariable String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(USER_SERVICE);

        String url = STR."http://\{serviceInstance.getHost()}:\{serviceInstance.getPort()}/user/\{name}";

        Object o = restTemplate.getForObject(url, Object.class);
        return R.success(o);
    }

    @GetMapping(value = "/user/v2/{name}")
    public Object getUserByName1(@PathVariable String name) {
        String url = STR."http://\{USER_SERVICE}/user/\{name}";
        Object o = restTemplateLoadBalanced.getForObject(url, Object.class);
        return R.success(o);
    }

    @GetMapping(value = "/user/v3/{name}")
    public Object getByName(@PathVariable String name) {
        Object o = userServiceClient.getByName(name);
        return R.success(o);
    }
}
