package cn.itcast.controller;

import cn.itcast.dubbo.api.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/19 9:51
 */
@Controller
@RequestMapping("/demo")
public class HelloController {
    @Reference
    private HelloService helloService;
    @RequestMapping("/hello")
    @ResponseBody
    public String getName(String name){
        //远程调用
        String result = helloService.sayHello(name);
        System.out.println(result);
        return result;
    }
}
