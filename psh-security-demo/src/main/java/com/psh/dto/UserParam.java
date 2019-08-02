package com.psh.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by peiyue.xing on 2019/7/29 18:34
 *
 * @version:
 */
public class UserParam {
    //wsagger接口参数
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "年龄")
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
