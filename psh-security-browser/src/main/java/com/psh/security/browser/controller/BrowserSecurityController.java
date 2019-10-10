package com.psh.security.browser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by peiyue.xing on 2019/10/10 11:54
 *
 * @version: 1.0
 */
@RestController
public class BrowserSecurityController {

    /**
     * 当需要身份认证时，跳转到这里
     * 判断引发跳转的请求是html请求，还是接口请求
     */
    @PostMapping("/authentication/require")
    public String authentication(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
