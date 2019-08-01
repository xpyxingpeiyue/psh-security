package com.psh.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by peiyue.xing on 2019/7/29 17:14
 * 先写测试用例  再写业务逻辑
 *
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    //这个方法在每个方法执行之前都会执行一遍
    @Before
    public void setup() {
        //初始化MockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String str = mockMvc.perform(MockMvcRequestBuilders.get("/user")//请求数据
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("username", "haha")
//                .param("page","5")
//                .param("size","18")
//                .param("sort","username")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())//请求结果期望是什么（状态码200）
                //https://github.com/json-path/JsonPath 帮助文档
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))//期望返回集合的长度为3
                .andReturn().getResponse().getContentAsString();
        System.out.println(str);
        ;


    }

    @Test
    public void whenGenInfoSuccess() throws Exception {
        String info = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())//restful请求是通过状态码来判断请求结果的
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(info);
    }
    @Test
    public void whenGetInfoFail() throws Exception {
        //验证地址校验
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());//期望返回错误
    }

    @Test
    public void wehnCreateSuccess() throws Exception {
        //404地址不匹配   405 method不匹配  400 参数异常

        Date date = new Date();
        //注意
        //前台传时间戳
        //后台自动把时间戳转为日期格式
        String content = "{\"username\":\"xing\",\"password\":\"123456\",\"birthday\":" + date.getTime() + "}";
        String str = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))//保存返回id
                .andReturn().getResponse().getContentAsString();
        ;
        System.out.println(str);
        //返回时客户端会自动把时间转为时间戳
        //{"id":"1","username":"ing","password":"12548","birthday":1564402295924}
    }
    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date(LocalDate.now().plusYears(1L).atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
        String content = "{\"id\":\"1\",\"username\":\"ing\",\"password\":\"123456\",\"birthday\":" + date.getTime() + "}";
        String str = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))//保存返回id
                .andReturn().getResponse().getContentAsString();
        System.out.println(str);
    }
    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
    }

}
