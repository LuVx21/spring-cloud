package org.luvx.cloud.service.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${server.port}")
    private int port;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/{name}")
    public Object getByName(@PathVariable String name) {
        return List.of(name, port, LocalDateTime.now(), discoveryClient.getServices());
    }
}
