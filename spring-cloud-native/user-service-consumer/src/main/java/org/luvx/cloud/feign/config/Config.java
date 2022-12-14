package org.luvx.cloud.feign.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import feign.Logger;

@Configuration
public class Config {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean("restTemplateLoadBalanced")
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
