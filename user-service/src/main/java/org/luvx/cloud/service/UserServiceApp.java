package org.luvx.cloud.service;

import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserServiceApp.class)
                .properties(Map.of("server.port", 8081))
                .run(args);
    }
}