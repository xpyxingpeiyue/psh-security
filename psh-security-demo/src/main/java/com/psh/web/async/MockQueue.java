package com.psh.web.async;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Created by peiyue.xing on 2019/8/2 15:13
 * 消息中间件
 * MockQueue 模拟队列存储消息
 *
 * @author peiyue.xing
 */
@Component
public class MockQueue {
    private static final Log log = LogFactory.get();
    private String placeOrder;//代表下单消息
    private String completeOrder;//代表订单完成消息

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        //单独的应用2程序来处理
        new Thread(() -> {
            log.info("接到下单请求:" + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("接到订单请求完成：" + completeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
