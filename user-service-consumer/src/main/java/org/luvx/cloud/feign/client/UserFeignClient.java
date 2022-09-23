package org.luvx.cloud.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
public interface UserFeignClient {
    @GetMapping(value = "/user/{name}")
    String getByName(@PathVariable("name") String name);
}
