package com.psh.config;

import com.psh.interceptor.TimerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by peiyue.xing on 2019/7/30 20:19
 * 继承 WebMvcConfigurerAdapter 使注解的interceptor生效
 *
 * @author peiyue.xing
 */
@Configuration
public class WebConfigAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    private TimerInterceptor timerInterceptor;//已经注解声明成组件

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timerInterceptor);
    }
}
