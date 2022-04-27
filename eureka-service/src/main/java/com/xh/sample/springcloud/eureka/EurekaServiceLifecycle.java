package com.xh.sample.springcloud.eureka;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * Create By IntelliJ IDEA
 *
 * @author: XieHua
 * @date: 2022-04-22 16:23
 */
@Component
public class EurekaServiceLifecycle implements SmartLifecycle {
    private boolean running;

    @Override
    public void start() {
        System.out.println("Spring 容器加载完成");
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
