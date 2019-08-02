package com.psh.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peiyue.xing on 2019/8/2 15:32
 * 消息
 * 模拟线程1和线程2之间的交互对象
 * @author peiyue.xing
 */
@Component
public class DeferredResultHolder {
    //key 代表订单号  value 代表每一个订单的处理结果
    private  Map<String, DeferredResult<String>> resultMap = new HashMap<>();

    public Map<String, DeferredResult<String>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, DeferredResult<String>> resultMap) {
        this.resultMap = resultMap;
    }
}
