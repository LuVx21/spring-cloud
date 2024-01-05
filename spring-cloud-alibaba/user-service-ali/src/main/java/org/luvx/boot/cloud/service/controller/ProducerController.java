package org.luvx.boot.cloud.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.luvx.boot.cloud.service.entity.User;
import org.luvx.boot.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping(value = "/mq")
public class ProducerController {
    @Autowired
    private StreamBridge streamBridge;

    @GetMapping(value = "/producer/v1")
    public R<Object> getUserByName() {
        List<User> list = IntStream.range(0, 5)
                .mapToObj(i -> {
                    String key = "KEY" + i;
                    Map<String, Object> headers = Map.of(MessageConst.PROPERTY_KEYS, key, MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, i);
                    User u = new User();
                    u.setUserName("userName" + i);
                    u.setAge(i);
                    Message<User> msg = new GenericMessage<>(u, headers);
                    streamBridge.send("producer-out-0", msg);
                    return u;
                })
                .collect(Collectors.toList());
        return R.success(list);
    }
}
