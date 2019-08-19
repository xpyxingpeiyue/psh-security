package com.psh.security.browser.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration//配置类
@EnableConfigurationProperties(SecurityProperties.class)//让配置文件类生效
public class SecurityCoreConfig {
}
