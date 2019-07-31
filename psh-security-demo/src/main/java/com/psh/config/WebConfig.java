package com.psh.config;

import com.psh.web.filter.TimerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peiyue.xing on 2019/7/30 19:27
 * 已配置文件的方式引入第三方filter
 * 第二种使用第三方方式
 * @author peiyue.xing
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean timerFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimerFilter timerFilter = new TimerFilter();
        //将filter假如filterbean (SpringBoot)
        filterRegistrationBean.setFilter(timerFilter);
        //指定拦截的controller地址
        List<String> urls = new ArrayList<>();
        urls.add("/*");//所有拦截
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;

    }
}
