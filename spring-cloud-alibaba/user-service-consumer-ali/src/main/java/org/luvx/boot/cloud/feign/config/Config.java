package org.luvx.boot.cloud.feign.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    /**
     * 不加负载均衡, 试用下LoadBalancerClient
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean("restTemplateLoadBalanced")
    @LoadBalanced
    public RestTemplate restTemplateLoadBalanced(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
