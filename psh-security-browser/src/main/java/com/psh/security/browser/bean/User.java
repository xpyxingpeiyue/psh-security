package com.psh.security.browser.bean;

import javax.persistence.*;

/**
 * Created by peiyue.xing on 2019/8/5 15:30
 *
 * @author peiyue.xing
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
