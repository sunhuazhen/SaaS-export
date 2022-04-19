package cn.itcast;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/18 18:13
 */
public class DemoProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-provider.xml");
        classPathXmlApplicationContext.start();
        System.in.read();
    }
}
