package com.xh.sample.springcloud.eureka;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create By IntelliJ IDEA
 *
 * @author: XieHua
 * @date: 2021-09-16 11:43
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
