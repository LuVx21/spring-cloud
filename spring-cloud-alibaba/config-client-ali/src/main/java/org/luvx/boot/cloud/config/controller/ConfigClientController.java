package org.luvx.boot.cloud.config.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigClientController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;
    @Value("${config.version}")
    private String configVersion;

    @GetMapping(value = "/config/client")
    public Object getConfig() {
        return ImmutableMap.of(
                "info", configInfo,
                "version", configVersion,
                "port", serverPort
        );
    }
}
