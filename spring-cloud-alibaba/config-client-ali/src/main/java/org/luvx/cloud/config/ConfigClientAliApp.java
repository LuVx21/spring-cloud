package org.luvx.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientAliApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientAliApp.class, args);
    }
}