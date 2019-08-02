package com.psh.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by peiyue.xing on 2019/7/30 20:58
 * 切面拦截
 * 表达式官方文档
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-ataspectj
 * 缺点：获取不到原始的request 和response请求
 *
 * @author peiyue.xing
 */
//@Aspect
//@Component
public class TimerAspect {
    @Around("execution(* com.psh.web.controller.UserController.*(..))")
//    @Around("timePoint()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("timer aspect start");
        //获取参数
        Stream.of(joinPoint.getArgs()).forEach(o -> System.out.println("arg is " + o));
        Object obj = joinPoint.proceed();//执行controller方法
        System.out.println("timer aspect end");
        return obj;
    }

    //二
    @Pointcut("execution(* com.psh.web.controller.UserController.*(..))")
    public void timePoint() {
        //标识
    }
}
