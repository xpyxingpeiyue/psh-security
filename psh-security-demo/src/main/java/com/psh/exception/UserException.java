package com.psh.exception;

/**
 * Created by peiyue.xing on 2019/7/30 11:45
 * 自定义异常信息
 *
 * @author peiyue.xing
 */
public class UserException extends RuntimeException {
    private String id;

    public UserException(String msg, String id) {
        super(msg);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
