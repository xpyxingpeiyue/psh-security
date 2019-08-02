package com.psh.config;

import com.psh.interceptor.TimerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by peiyue.xing on 2019/7/30 20:19
 * 继承 WebMvcConfigurerAdapter 使注解的interceptor生效
 *
 * @author peiyue.xing
 */
//@Configuration
public class WebConfigAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    private TimerInterceptor timerInterceptor;//已经注解声明成组件


    /**
     * 对于异步请求的拦截处理方法  和同步请求不一样
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // configurer.registerCallableInterceptors();//preHandle   postHandle  等注册拦截器
        // configurer.registerDeferredResultInterceptors();//preHandle   postHandle  等注册拦截器
        // configurer.setDefaultTimeout();//默认异步线程超时时间
        // configurer.setTaskExecutor();//runnable 异步调用时，每次开启新的线程，这里可以自定义可重用线程池
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timerInterceptor);
    }
}
