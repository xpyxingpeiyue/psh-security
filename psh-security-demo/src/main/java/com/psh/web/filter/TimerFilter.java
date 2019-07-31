package com.psh.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by peiyue.xing on 2019/7/30 18:57
 * 使用原生的servlet 的filter实现过滤请求
 * 缺点：只能拿到request和response的请求，因为不属于spring，所以无法获取是有那个controller的那个方法发送的请求
 * @author peiyue.xing
 */
//第一种方式添加到spring容器中  注解方式
//@Component
public class TimerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //程序开启时执行一次
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do filter");
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();
        System.out.println("执行消耗时间：" + (endTime - startTime));
    }

    @Override
    public void destroy() {
        //服务关闭时执行 一次
        System.out.println("filter destroy");
    }
}
