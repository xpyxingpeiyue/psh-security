package com.psh.web.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.psh.web.async.DeferredResultHolder;
import com.psh.web.async.MockQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Created by peiyue.xing on 2019/8/2 10:21
 * 异步处理请求
 *
 * @author peiyue.xing
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    private static final Log log = LogFactory.get();

    //同步操作
    @GetMapping("/syncTest")
    public Object sync() {
        log.info("主线程开始");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程返回");
        //nio-8080-exec  tomcat的主线程
        /*
        2019-08-02 10:54:34.708  INFO 2660 --- [nio-8080-exec-2] com.psh.web.controller.AsyncController   : 主线程开始
        2019-08-02 10:54:35.211  INFO 2660 --- [nio-8080-exec-1] com.psh.web.controller.AsyncController   : 主线程开始
        2019-08-02 10:54:36.434  INFO 2660 --- [nio-8080-exec-3] com.psh.web.controller.AsyncController   : 主线程开始
        2019-08-02 10:54:44.708  INFO 2660 --- [nio-8080-exec-2] com.psh.web.controller.AsyncController   : 主线程返回
        2019-08-02 10:54:45.212  INFO 2660 --- [nio-8080-exec-1] com.psh.web.controller.AsyncController   : 主线程返回
        2019-08-02 10:54:46.434  INFO 2660 --- [nio-8080-exec-3] com.psh.web.controller.AsyncController   : 主线程返回
        */
        return "success";
    }

    /**
     * 异步后
     * Runnable 异步处理Rest服务
     * tomcat的主线程 可以立即处理其他线程，结果交友副线程去处理
     * 提高系统的吞吐量
     */
    @GetMapping("/asyncTest")
    public Object async() {
        log.info("主线程开始");
        Callable<String> result = () -> {
            log.info("副线程开始。。。。");
            Thread.sleep(1000 * 10);
            log.info("副线程结束。。。。");
            return "success";
        };
        log.info("主线程结束");
        //主线程开启后立即返回，没有任何停顿，主线程和副线程没有必然联系
        /*
        2019-08-02 10:59:37.870  INFO 4024 --- [nio-8080-exec-5] com.psh.web.controller.AsyncController   : 主线程开始
        2019-08-02 10:59:37.870  INFO 4024 --- [nio-8080-exec-5] com.psh.web.controller.AsyncController   : 主线程结束
        2019-08-02 10:59:37.871  INFO 4024 --- [      MvcAsync7] com.psh.web.controller.AsyncController   : 副线程开始。。。。
        2019-08-02 10:59:38.921  INFO 4024 --- [nio-8080-exec-6] com.psh.web.controller.AsyncController   : 主线程开始
        2019-08-02 10:59:38.921  INFO 4024 --- [nio-8080-exec-6] com.psh.web.controller.AsyncController   : 主线程结束
        2019-08-02 10:59:38.922  INFO 4024 --- [      MvcAsync8] com.psh.web.controller.AsyncController   : 副线程开始。。。。
        2019-08-02 10:59:47.871  INFO 4024 --- [      MvcAsync7] com.psh.web.controller.AsyncController   : 副线程结束。。。。
        2019-08-02 10:59:48.923  INFO 4024 --- [      MvcAsync8] com.psh.web.controller.AsyncController   : 副线程结束。。。。
        */
        //结果还是等待副线程执行完返回
        return result;
    }

    /*
     * DeferredResult异步处理Rest服务
     *  以消息队列的方式异步处理请求
     * 应用A<->消息队列<->应用B
     * */
    //消息队列生成消息
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/defAsynn")
    public DeferredResult<String> deferrAsync() throws InterruptedException {
        log.info("主线程开始");
        String orderNumber = RandomUtil.randomNumbers(8);
        mockQueue.setPlaceOrder(orderNumber);//产生消息
        DeferredResult<String> result = new DeferredResult<>();//结果
        deferredResultHolder.getResultMap().put(orderNumber, result);
        log.info("主线程返回");
        return result;
    }
}
