package com.psh.security.browser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * Created by peiyue.xing on 2019/8/3 12:35
 * security 配置
 * 安全：认证（authentication ）  授权 （authorization）
 *
 * 原理
 * 请求->(UsernamePasswordAuthenticationFilter)(表单请求方式filter)->BasicAuthenticationFilter(默认请求filter)、、、->ExceptionTranslationFilter(用来捕获后面一个过滤器抛出的异常)->FilterSecurityInterceptor(Spring Security 过滤器的最后一环，所有前面都会经过这一环)
 *
 * Spring Security 过滤器链->TESTful
 * @author peiyue.xing
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//            http.httpBasic();//默认弹出窗模式
        http.formLogin()//规定表单登录方式
                .and()
                .authorizeRequests()//然后授权请求方式
                .anyRequest()//任何请求
                .authenticated();//都需要身份认证
    }
}
