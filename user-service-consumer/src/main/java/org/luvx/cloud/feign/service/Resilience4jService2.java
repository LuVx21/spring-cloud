package org.luvx.cloud.feign.service;

import java.time.Duration;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;

@Service
// @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "backendA")
public class Resilience4jService2 {
    @Resource
    private UserFeignClient        userFeignClient;
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public String breaker1(boolean exception) {
        return userFeignClient.getByNameException(exception);
    }

    public String breaker2(boolean exception) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(1))
                .slidingWindow(5, 5, SlidingWindowType.COUNT_BASED)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
        CircuitBreaker circuitBreaker =
                circuitBreakerRegistry.circuitBreaker("backendA", circuitBreakerConfig);
        Try<String> result = Try.ofSupplier(CircuitBreaker.decorateSupplier(
                        circuitBreaker, () -> userFeignClient.getByNameException(exception)
                ))
                .recover(Exception.class, "有异常，访问失败！");
        return result.get();
    }
}