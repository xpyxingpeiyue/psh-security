package com.psh.handler;

import com.psh.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peiyue.xing on 2019/7/30 12:02
 * 自定义异常处理控制器
 * 不会再区分浏览器和接口访问（postman）返回不同信息（默认异常不执行）
 * @author peiyue.xing
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UserException.class)
    @ResponseBody//将返回的map转为一个json
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回的错误状态码
    public Map<String, Object> userNotExistException(UserException ex) {
        //处理补货的异常
        Map<String, Object> map = new HashMap<>();
        map.put("id", ex.getId());
        map.put("message", ex.getMessage());
        return map;
    }
}
