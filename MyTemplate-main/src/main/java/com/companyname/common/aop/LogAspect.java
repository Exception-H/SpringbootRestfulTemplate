package com.companyname.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 全局日志打印
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.companyname.projectname.controller.*.*(..))")
    public void ControllLog(){}

    @Before("ControllLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // url

        log.info("url={}", request.getRequestURL());
        // method(Get/Post...)
        log.info("method={}", request.getMethod());
        // ip
        log.info("ip={}", request.getRemoteAddr());
        // 类方法
        String[] nameList = joinPoint.getSignature().getDeclaringTypeName().split("\\.");
        log.info("class_method={}", nameList[nameList.length-1] + "." + joinPoint.getSignature().getName());
        // 参数
        log.info("args={}", Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "resp", pointcut = "ControllLog()")
    public void doAfter(Object resp) {
        log.info("response={}", resp);
    }
}
