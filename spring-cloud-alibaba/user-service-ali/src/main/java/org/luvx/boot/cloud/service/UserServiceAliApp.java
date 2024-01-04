package org.luvx.boot.cloud.service;

import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.luvx.boot"})
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceAliApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserServiceAliApp.class)
                .properties(Map.of("server.port", 8081))
                .run(args);
    }
}