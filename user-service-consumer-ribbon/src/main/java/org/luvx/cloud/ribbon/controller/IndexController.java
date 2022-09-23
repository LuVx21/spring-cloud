package org.luvx.cloud.ribbon.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/ribbon/user/{name}")
    public String getUserByName(@PathVariable String name) {
        String serviceId = "user-service";
        return restTemplate.getForObject("http://" + serviceId + "/user/" + name, String.class);
    }
}
