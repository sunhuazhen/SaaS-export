package cn.itcast.dubbo.api.impl;

import cn.itcast.dubbo.api.HelloService;
import org.springframework.stereotype.Service;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/18 18:07
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello"+name;
    }
}
