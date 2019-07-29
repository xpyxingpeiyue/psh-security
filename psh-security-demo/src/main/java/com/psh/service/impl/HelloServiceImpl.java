package com.psh.service.impl;

import com.psh.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by peiyue.xing on 2019/7/29 21:21
 *
 * @version:
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(Object msg) {
        return "hello" + msg;
    }
}
