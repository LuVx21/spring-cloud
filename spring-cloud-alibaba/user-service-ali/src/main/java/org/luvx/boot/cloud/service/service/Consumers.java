package org.luvx.boot.cloud.service.service;

import lombok.extern.slf4j.Slf4j;
import org.luvx.boot.cloud.service.entity.User;
import org.luvx.coding.common.net.NetUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class Consumers {
    @Bean
    public Consumer<Message<User>> consumer() {
        return msg -> {
            log.info("收到消息: {} {}", NetUtils.getHostInfo(), msg.getPayload());
        };
    }
}
