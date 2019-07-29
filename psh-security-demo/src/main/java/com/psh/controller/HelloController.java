package com.psh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by peiyue.xing on 2019/7/29 16:10
 *
 * @version:
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello() {
        return "hello spring security";
    }
}
