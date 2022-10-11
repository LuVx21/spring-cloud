package org.luvx.cloud.feign.service;

import java.time.Duration;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;

@Service
//@CircuitBreaker(name = "backendA")
public class HelloServiceCircuitBreaker {
    @Resource
    private UserFeignClient        userFeignClient;
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public String hello(String name) {
        return userFeignClient.getByNameException(true);
    }

    public String hello2(String name) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .ringBufferSizeInHalfOpenState(20)
                .ringBufferSizeInClosedState(20)
                .build();
        CircuitBreaker circuitBreaker =
                circuitBreakerRegistry.circuitBreaker("backendA", circuitBreakerConfig);
        Try<String> result = Try.ofSupplier(CircuitBreaker.decorateSupplier(
                        circuitBreaker, () -> userFeignClient.getByNameException(true)
                ))
                .recover(Exception.class, "有异常，访问失败！");

        return result.get();
    }
}