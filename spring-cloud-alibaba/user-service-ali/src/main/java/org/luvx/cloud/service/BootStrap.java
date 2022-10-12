package org.luvx.cloud.service;

import java.util.Map;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 启动多个服务
 */
public class BootStrap {
    public static void main(String[] args) {
        start(8081).run(args);
        start(8082).run(args);
    }

    private static SpringApplicationBuilder start(int port) {
        return new SpringApplicationBuilder(UserServiceAliApp.class)
                .properties(Map.of("server.port", port));
    }
}
