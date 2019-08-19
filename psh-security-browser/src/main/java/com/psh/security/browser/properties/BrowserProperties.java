package com.psh.security.browser.properties;

public class BrowserProperties {
    //配置登录页，不同项目配置不同的登陆效果，如果不配置时，使用默认登录页面
    private String pageLogin = "/page/login/login.html";

    public String getPageLogin() {
        return pageLogin;
    }

    public void setPageLogin(String pageLogin) {
        this.pageLogin = pageLogin;
    }
}
