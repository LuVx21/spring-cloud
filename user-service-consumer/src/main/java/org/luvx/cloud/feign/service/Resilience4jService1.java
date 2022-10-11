package org.luvx.cloud.feign.service;

import javax.annotation.Resource;

import org.luvx.cloud.feign.client.UserFeignClient;
import org.springframework.stereotype.Service;

import io.github.resilience4j.retry.annotation.Retry;

@Service
@Retry(name = "retryBackendA")
public class Resilience4jService1 {

    @Resource
    private UserFeignClient userFeignClient;

    public String retry(boolean exception) {
        return userFeignClient.getByNameException(exception);
    }
}