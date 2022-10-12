package org.luvx.cloud.feign.controller;

import java.time.Duration;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.luvx.cloud.feign.service.Resilience4jService1;
import org.luvx.cloud.feign.service.Resilience4jService2;
import org.luvx.cloud.feign.service.Resilience4jService3;
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
    private Resilience4jService1 service1;
    @Autowired
    private Resilience4jService2 service2;
    @Autowired
    private Resilience4jService3 service3;

    /**
     * 声明式重试
     */
    @GetMapping("/retry1")
    public String retry1(boolean exception) {
        return service1.retry(exception);
    }

    /**
     * 编程式重试
     */
    @GetMapping("/retry2")
    public String retry2(boolean exception) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(5000))
                .build();
        Retry retry = Retry.of("id", config);
        Try<String> result = Try.ofSupplier(Retry.decorateSupplier(
                retry, () -> service1.retry(exception)
        ));
        return result.get();
    }

    @GetMapping("/breaker1")
    public String breaker1(boolean exception) {
        return service2.breaker1(exception);
    }

    @GetMapping("/breaker2")
    public String breaker2(boolean exception) {
        return service2.breaker2(exception);
    }

    @GetMapping("/rl1")
    public void rl1(String name) {
        for (int i = 0; i < 5; i++) {
            log.info(name);
            String hello = service3.rl1(name);
        }
    }

    @GetMapping("/rl2")
    public void rl2(String name) {
        service3.rl2(name);
    }
}
