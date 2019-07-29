package com.psh.web.controller;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.psh.dto.User;
import com.psh.dto.UserParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peiyue.xing on 2019/7/29 17:23
 * JsonView 指定某个字段是否返回给前台
 * 使用步骤：
 * 使用接口来声明多个视图
 * 在值对象的get方法上指定视图
 * 在controller方法上指定视图
 *
 * @version:1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query(@RequestParam String username) {
        System.out.println(username);
        return getUsers();
    }

    /**
     * Pageable pageable 分页  spring-data
     * PageableDefault 默认分数数据
     *
     * @author peiyue.xing
     */
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping()//重构
    @JsonView(User.UserSimpleView.class)
    public List<User> query2(UserParam userParam, @PageableDefault(size = 15, page = 1, sort = {"username"}) Pageable pageable) {
        System.out.println(JSONUtil.parseObj(userParam));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        return getUsers();
    }

    /**
     * PathVariable 正则限制  url做限制
     * 正则验证只能为数字
     *
     * @param id
     */
//    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserComplexView.class)
    public User getInfo(@PathVariable("id") String id) {
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    /**
     * @RequestBody 用于post请求application/json json格式数据
     * get请求不需要
     *
     * Hibernate Validator API文档
     * 1、java自带注解需要配合@Valid 才能生效，无法执行方法体
     * 2、BindingResult 存储注解的错误信息，可以继续执行方法体
     * 3、添加自定义注解
     * @author peiyue.xing
     */

    @PostMapping
    public User create(@Valid  @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());//自动转为日期
        user.setId("1");
        return user;
    }
    @PutMapping("/{id:\\d+}")
    public User update(@Valid  @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error-> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());//自动转为日期
        user.setId("1");
        return user;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User u = new User();
        u.setUsername("xing");
        u.setPassword("123456");
        User u1 = new User();
        u1.setUsername("xing2");
        u1.setPassword("123456");
        User u2 = new User();
        u2.setUsername("xing3");
        u2.setPassword("123456");
        users.add(u);
        users.add(u1);
        users.add(u2);
        return users;
    }
}
