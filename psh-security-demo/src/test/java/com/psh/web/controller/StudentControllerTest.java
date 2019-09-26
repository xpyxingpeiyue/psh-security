package com.psh.web.controller;


import com.sun.corba.se.impl.orbutil.ObjectWriter;
import org.apache.pdfbox.io.IOUtils;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by peiyue.xing on 2019/9/26 10:15
 */
public class StudentControllerTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("邢培月");
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("tempFile"));
            outputStream.writeObject(user);
            System.out.println("对象写入");
        }catch (Exception e){
            System.out.println("异常");
        }finally {
            IOUtils.closeQuietly(outputStream);
        }

    }

}
class User implements Serializable {
    private static final long serialVersionUID = 3215876671575167887L;
    private String name;
    private String age;
    private String adress;
    public User(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
