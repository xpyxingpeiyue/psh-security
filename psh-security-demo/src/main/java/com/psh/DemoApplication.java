package com.psh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by peiyue.xing on 2019/7/29 14:20
 * @author peiyue.xing
 */
@SpringBootApplication
@EnableSwagger2//启动swagger服务
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }
}
