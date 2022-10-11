package org.luvx.cloud.feign.controller;

import java.time.Duration;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.luvx.cloud.feign.service.HelloService;
import org.luvx.cloud.feign.service.HelloServiceCircuitBreaker;
import org.luvx.cloud.feign.service.HelloServiceRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Resilience4jController {
    @Resource
    private UserFeignClient userFeignClient;

    @Autowired
    private HelloService               helloService;
    @Autowired
    private HelloServiceCircuitBreaker serviceCircuitBreaker;
    @Autowired
    private HelloServiceRateLimiter    helloServiceRateLimiter;

    /**
     * 声明式重试
     */
    @GetMapping("/retry1")
    public String hello(String name) {
        return helloService.hello(name);
    }

    /**
     * 编程式重试
     */
    @GetMapping("/retry2")
    public String hello2(String name) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(5000))
                .build();
        Retry retry = Retry.of("id", config);
        Try<String> result = Try.ofSupplier(Retry.decorateSupplier(
                retry, () -> helloService.hello(name)
        ));
        return result.get();
    }

    @GetMapping("/breaker1")
    public String hello3(String name) {
        return serviceCircuitBreaker.hello(name);
    }

    @GetMapping("/breaker2")
    public String hello4(String name) {
        return serviceCircuitBreaker.hello2(name);
    }


    @GetMapping("/rl1")
    public void rateLimiter(String name) {
        for (int i = 0; i < 5; i++) {
            log.info(name);
            String hello = helloServiceRateLimiter.hello(name);
        }
    }

    @GetMapping("/rl2")
    public void rateLimiter2(String name) {
        helloServiceRateLimiter.hello2(name);
    }
}
