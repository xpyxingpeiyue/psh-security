package com.psh.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by peiyue.xing on 2019/7/30 19:51
 * 使用interceptor  spring封装的处理拦截请求
 *org.springframework.boot.autoconfigure.web.BasicErrorController 也会拦截，作用于controller拦截
 *
 * 缺点 ： 只能获取类和方法，但是（preHandle）无法获取（handler）参数
 * DispatcherServlet->doService->doDispatch
 * applyPreHandle没有对参数做处理
 * if (!mappedHandler.applyPreHandle(processedRequest, response)) {
 *      return;
 * }
 *  mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
 *
 * 要想获取参数只能使用切片
 * 切片（类）
 * 切入点（注解）
 * 在那些方法上起作用，在什么时候起作用
 *
 * 增加（方法）
 * 起作用时执行的业务逻辑
 * @author peiyue.xing
 */
//声明注解还无法起作用还需要配置
@Component
public class TimerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //方法执行前执行
        System.out.println("preHandle 。。。。");
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        //方法间数据传递 通过attribute
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        return true;//布尔值决定是否调用postHandle  即是否继续调用controller中的方法
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        //方法体运行后执行  异常时不会执行
        System.out.println("postHandle。。。。");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("方法执行耗时：" + (System.currentTimeMillis() - startTime));

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {
        //是否异常都会执行
        System.out.println("afterCompletion.....");
        //成功时  ex 为null
        //失败时才有 ex
        System.out.println("ex is " + ex);
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("方法执行耗时：" + (System.currentTimeMillis() - startTime));
    }
}
