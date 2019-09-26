package com.psh.web.controller;

import cn.hutool.json.JSONUtil;
import org.apache.pdfbox.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by peiyue.xing on 2019/9/26 14:04
 *
 * @version:
 */
public class SerializableTest {
    public static void main(String[] args) {
        File file = new File("tempFile");
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
//            Object  obj = inputStream.readObject();
//            System.out.println(JSONUtil.parseObj(obj).toString());
            User user = (User) inputStream.readObject();
            System.out.println("user"+user);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            try {
//                FileUtils.forceDelete(file);
            } catch (Exception e1) {
                System.out.println(e1);
            }

        }

    }
}
