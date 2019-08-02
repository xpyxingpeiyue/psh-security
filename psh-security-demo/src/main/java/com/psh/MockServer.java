package com.psh;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.nio.charset.Charset;

/**
 * Created by peiyue.xing on 2019/8/2 18:26
 * 提供前台测试数据
 * 有专门整合SpringBoot的例子
 * @author peiyue.xing
 */
public class MockServer {
    public static void main(String[] args) {
        WireMock.configureFor("127.0.0.1", 8092);//本地  连接服务
        WireMock.removeAllMappings();//移出所有的请求，生成新的请求
//        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/order/1"))
//                .willReturn(
//                        WireMock.aResponse().withBody("{\"id\":1}").withStatus(200))
//        );//测试桩数据
        //一闪而过，将程序结果注册进去


        //使用读取文件的方式读取mock  classpath:/mock/response/order.json
        String str = ResourceUtil.readStr("mock/response/order.json", Charset.forName("UTF-8"));
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/order/1"))
                .willReturn(
                        WireMock.aResponse().withBody(str).withStatus(200).withHeader("Content-Type","application/json;charset=UTF-8"))//解决中文乱码问题
        );//测试桩数据
    }
}
