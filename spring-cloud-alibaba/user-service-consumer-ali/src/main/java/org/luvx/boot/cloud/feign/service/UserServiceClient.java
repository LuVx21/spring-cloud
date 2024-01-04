package org.luvx.boot.cloud.feign.service;

import org.luvx.boot.cloud.feign.consts.ServiceHolder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.luvx.boot.cloud.feign.consts.ServiceHolder.USER_SERVICE;

@FeignClient(name = USER_SERVICE)
public interface UserServiceClient {
    @GetMapping(value = "/user/{name}")
    Object getByName(@PathVariable("name") String name);
}
