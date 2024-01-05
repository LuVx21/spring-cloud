package org.luvx.boot.rpc.dubbo.service.impl.impl;

import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.DubboService;
import org.luvx.boot.rpc.dubbo.sdk.UserService;
import org.luvx.coding.common.net.NetUtils;

import java.util.List;

@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public List<Object> getByName(String name) {
        return Lists.newArrayList(
                NetUtils.getHostInfo(), name + '0', name, name + '1'
        );
    }
}