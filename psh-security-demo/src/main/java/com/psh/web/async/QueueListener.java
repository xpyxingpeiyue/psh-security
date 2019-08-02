package com.psh.web.async;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by peiyue.xing on 2019/8/2 15:53
 * 监听器 监听消息队列的变化
 *
 * @author peiyue.xing
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Log log = LogFactory.get();
    //ContextRefreshedEvent 整个spring容器的事件
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //监听  异步执行
        new Thread(() -> {
            while (true) {
                //订单完成处理逻辑
                if (StrUtil.isNotBlank(mockQueue.getCompleteOrder())) {
                    String orderNumber = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果：" + orderNumber);
                    //完成逻辑返回结果
                    deferredResultHolder.getResultMap().get(orderNumber).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        //等待继续执行
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
