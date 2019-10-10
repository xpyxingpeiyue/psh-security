package com.psh.security.browser.config;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义失败处理器
 */
@Component
public class AuthFailHandler implements AuthenticationFailureHandler {
    private static final Log log = LogFactory.get();

    /**
     * @param request
     * @param response
     * @param authenticationException 登录失败 所以不是authentication 而是失败信息，封装失败原因
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        log.info("登录失败");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.parseObj(authenticationException).toString());
    }
}
