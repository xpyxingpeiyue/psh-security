package com.psh.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * Created by peiyue.xing on 2019/8/3 12:35
 * security 配置
 * 安全：认证（authentication ）  授权 （authorization）
 * <p>
 * 原理
 * 请求->(UsernamePasswordAuthenticationFilter)(表单请求方式filter)->BasicAuthenticationFilter(默认请求filter)、、、->ExceptionTranslationFilter(用来捕获后面一个过滤器抛出的异常)->FilterSecurityInterceptor(Spring Security 过滤器的最后一环，所有前面都会经过这一环)
 * <p>
 * Spring Security 过滤器链->TESTful
 *
 * @author peiyue.xing
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    //定义加密解密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();//这是springsecurity定义的PasswordEncoder ，如果想用自己的方式秩序实现PasswordEncoder 就行
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//            http.httpBasic();//默认弹出窗模式
        http.formLogin()//规定表单登录方式
                .loginPage("/page/login/login.html")//指定登录页
                .and()
                .authorizeRequests()//然后授权请求方式
                .antMatchers("/page/login/login.html","/**/*.css","/**/*.js","/favicon.ico").permitAll()//指定不拦截
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();//关闭防御攻击
    }
}
