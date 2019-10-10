package com.psh.security.browser.config;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义成功登录结果
 * 登录成功后自己定义返回的信息  不再是默认登录成功跳转链接地址
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private static final Log log = LogFactory.get();

    /**
     * @param authentication 封装认证信息  UserDetails
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.parse(authentication).toString());
        log.info("已定义返回信息");
    }
}
