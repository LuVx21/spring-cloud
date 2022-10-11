package org.luvx.cloud.feign.client;

import static org.luvx.cloud.feign.consts.ServiceHolder.USER_SERVICE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = USER_SERVICE)
public interface UserFeignClient {
    @GetMapping(value = "/user/{name}")
    String getByName(@PathVariable("name") String name);

    @GetMapping(value = "/user/name/timeout")
    String getByNameTimeOut(int time);

    @GetMapping(value = "/user/name/exception")
    String getByNameException(boolean exception);
}
