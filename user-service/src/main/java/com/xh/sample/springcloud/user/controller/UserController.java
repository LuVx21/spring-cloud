package com.xh.sample.springcloud.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Create By IntelliJ IDEA
 *
 * @author: XieHua
 * @date: 2021-10-19 15:39
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/{name}")
    public String getByName(@PathVariable String name) {
        System.out.println(port);
        return name;
    }
}
