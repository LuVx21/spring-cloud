package org.luvx.cloud.eureka;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServiceApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
