package com.psh.web.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.psh.web.filter.wrapper.RequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by peiyue.xing on 2019/8/1 18:40
 * 注意：HttpServletRequest 请求中的 body 内容仅能调用 request.getInputStream()， request.getReader()和request.getParameter("key") 方法读取一次，
 * 重复读取会报 java.io.IOException: Stream closed 异常。
 * 对于请求的参数做拦截,继承HttpServletRequestWrapper  封装请求
 *
 *
 *
 * 首先，我们复习一下InputStream read方法的基础知识，
 *
 * java InputStream read方法内部有一个，postion，标志当前流读取到的位置，每读取一次，位置就会移动一次，如果读到最后，InputStream.read方法会返回-1，标志已经读取完了，如果想再次读取，可以调用inputstream.reset方法，position就会移动到上次调用mark的位置，mark默认是0，所以就能从头再读了。
 *
 * 当然，能否reset是有条件的，它取决于markSupported,markSupported() 方法返回是否可以mark/reset
 *
 * 我们再回头看request.getInputStream
 *
 * request.getInputStream返回的值是ServletInputStream,查看ServletInputStream源码发现，没有重写reset方法，所以查看InputStream源码发现marksupported 返回false，并且reset方法，直接抛出异常。
 *
 * InputStream.java
 *
 * public boolean markSupported() {
 *    return false;
 *  }
 *
 * public synchronized void reset() throws IOException {
 *    throw new IOException("mark/reset not supported");
 *  }
 *
 *
 * @author peiyue.xing
 */
//@Component
public class ParamsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("params init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        //这种方式整合interceptor可以多次调用request  先filter 在interceptor 可以直接使用requset（多次使用效果）
        RequestWrapper requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        }
        if(requestWrapper == null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{

            filterChain.doFilter(requestWrapper,servletResponse);
        }*/

        //这种方式不结合spring  单独使用 可行
        RequestWrapper requestWrapper = requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        JSONObject json = JSONUtil.parseObj(requestWrapper.getBody());
        System.out.println("request:*****"+json);
        filterChain.doFilter(requestWrapper,servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("params destroy");
    }
}
