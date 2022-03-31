package com.xh.sample.springcloud.product;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create By IntelliJ IDEA
 *
 * @author: XieHua
 * @date: 2022-03-31 16:18
 */
@RestController
@SpringBootApplication
public class ProductServiceApplication {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping(value = "/product/user/{name}")
    public String getUserByName(@PathVariable String name) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("user-service");
        int index = RandomUtils.nextInt(instanceList.size());
        ServiceInstance serviceInstance = instanceList.get(index);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + name;

        return restTemplate.getForObject(url, String.class);
    }
}
