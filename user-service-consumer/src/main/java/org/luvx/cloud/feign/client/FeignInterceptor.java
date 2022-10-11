package org.luvx.cloud.feign.client;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        log.info("Feign请求: {} {}", template.path(), LocalDateTime.now());
    }
}