package cn.itcast.controller.log;

import cn.itcast.domain.system.SysLog;
import cn.itcast.domain.system.User;
import cn.itcast.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/15 15:36
 */
@Component
@Aspect
public class AspectLog {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @Around("execution(* cn.itcast.controller.*.*.*(..))")
    public Object saveLog(ProceedingJoinPoint pjp) throws Throwable {
        SysLog sysLog = new SysLog();

        sysLog.setTime(new Date());
        sysLog.setIp(request.getRemoteAddr());
        sysLog.setId(UUID.randomUUID().toString());

        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            sysLog.setUserName(user.getUserName());
            sysLog.setCompanyId(user.getCompanyId());
            sysLog.setCompanyName(user.getCompanyId());
        }
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();//获取方法签名 方法+注解
        Method method = methodSignature.getMethod();
        String name = method.getName();
        sysLog.setMethod(name);
        //判断方法上是否有requestmapping注解
        if (method.isAnnotationPresent(RequestMapping.class)){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String name1 = requestMapping.name();
            sysLog.setAction(name1);  //方法上RequestMapping注解的name值
        }
        sysLogService.save(sysLog);
        return pjp.proceed();//运行原方法
    }
}
