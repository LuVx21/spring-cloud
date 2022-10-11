package org.luvx.cloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RibbonConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApp.class, args);
    }
}
