package org.luvx.boot.cloud.service.controller;

import com.github.phantomthief.util.MoreFunctions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${server.port}")
    private int port;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/{name}")
    public Object getByName(@PathVariable String name) {
        String net = MoreFunctions.catching(() -> {
            InetAddress ia = InetAddress.getLocalHost();
            String host = ia.getHostName(), ip = ia.getHostAddress();
            return STR."\{ip} \{host}";
        });
        List<Object> info = info();
        info.add(net);
        return info;
    }

    @GetMapping(value = "/name/timeout")
    public Object getByNameTimeOut(int time) {
        MoreFunctions.runCatching(() -> TimeUnit.SECONDS.sleep(time));
        return info();
    }

    @GetMapping(value = "/name/exception")
    public Object getByNameException(boolean exception) {
        if (exception) {
            throw new RuntimeException();
        }
        return info();
    }

    private List<Object> info() {
        return Lists.newArrayList(port, LocalDateTime.now(), discoveryClient.getServices());
    }
}
