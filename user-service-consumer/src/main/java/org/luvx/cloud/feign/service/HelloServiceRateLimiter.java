package org.luvx.cloud.feign.service;

import java.time.Duration;
import java.util.function.Supplier;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.control.Try;

@Service
//@RateLimiter(name = "backendA")
public class HelloServiceRateLimiter {
    @Resource
    private UserFeignClient userFeignClient;

    public String hello(String name) {
        return userFeignClient.getByNameException(true);
    }

    public void hello2(String name) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1000))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofMillis(6000))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiter = RateLimiter.of("backendB", config);
        Supplier<String> supplier = RateLimiter.decorateSupplier(
                rateLimiter, () -> userFeignClient.getByNameException(true)
        );
        for (int i = 0; i < 5; i++) {
            Try<String> result = Try.ofSupplier(supplier);
            System.out.println(result.get());
        }
    }
}