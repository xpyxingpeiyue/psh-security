package com.psh.web.filter.wrapper;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by peiyue.xing on 2019/8/1 18:43
 * 通过HttpServletRequestWrapper（装饰模式的应用）增强HttpServletRequest的功能(转载)
 *
 * @author peiyue.xing
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private String body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        InputStream in = null;
        try {
            in = request.getInputStream();
            body = IoUtil.read(in, Charset.forName("UTF-8"));
        } catch (IOException e) {
            StaticLog.error(e);
        } finally {
            IoUtil.closeIfPosible(in);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(StrUtil.utf8Bytes(body));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public String getBody() {
        return body;
    }
}
