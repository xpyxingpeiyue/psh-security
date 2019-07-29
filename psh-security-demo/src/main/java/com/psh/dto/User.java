package com.psh.dto;


import com.fasterxml.jackson.annotation.JsonView;
import com.psh.annotation.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by peiyue.xing on 2019/7/29 17:25
 *
 * @version:
 */
public class User implements Serializable {
    public interface UserSimpleView{};//公共显示
    public interface UserComplexView extends UserSimpleView{};//特殊显示

    private String id;


    @MyConstraint(message = "用户名不能为空")
    private String username;
    @NotBlank//使用java自带常用注解判断
    private String password;

    //对于时间格式数据，最好返回给前台时间戳  前后端分离时，前端的格式是不确定的，由前台自己处理
    //前台传递的也应该是时间戳
    @Past(message = "生日必须为过去时间")//必须为过去时间
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserComplexView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
