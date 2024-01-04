package org.luvx.boot.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"org.luvx.boot"})
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceConsumerAliApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceConsumerAliApp.class, args);
    }
}
