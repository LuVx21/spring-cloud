package org.luvx.boot.rpc.dubbo.sdk;

import java.util.List;

public interface UserService {
    List<Object> getByName(String name);
}