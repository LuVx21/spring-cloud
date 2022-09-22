package com.xh.sample.springcloud.ribbon;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
// @RequestMapping(value = "/user")
public class IndexController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/ribbon/user/{name}")
    public String getUserByName(@PathVariable String name) {
        return restTemplate.getForObject("http://user-service/user/" + name, String.class);
    }
}
