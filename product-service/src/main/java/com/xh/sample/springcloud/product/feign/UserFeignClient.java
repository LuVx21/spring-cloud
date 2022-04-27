package com.xh.sample.springcloud.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Create By IntelliJ IDEA
 *
 * @author: XieHua
 * @date: 2022-04-22 11:49
 */
@FeignClient(value = "user-service")
public interface UserFeignClient {
    @GetMapping(value = "/user/{name}")
    String getByName(@PathVariable String name);
}
