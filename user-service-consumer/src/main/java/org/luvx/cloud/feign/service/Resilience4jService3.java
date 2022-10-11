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
@io.github.resilience4j.ratelimiter.annotation.RateLimiter(name = "backendA")
public class Resilience4jService3 {
    @Resource
    private UserFeignClient userFeignClient;

    public String rl1(String name) {
        return userFeignClient.getByName(name);
    }

    public void rl2(String name) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)
                .limitRefreshPeriod(Duration.ofSeconds(5))
                .timeoutDuration(Duration.ofSeconds(6))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiter = RateLimiter.of("backendB", config);
        Supplier<String> supplier = RateLimiter.decorateSupplier(
                rateLimiter, () -> userFeignClient.getByName(name)
        );
        for (int i = 0; i < 5; i++) {
            Try<String> result = Try.ofSupplier(supplier);
            System.out.println(result.get());
        }
    }
}