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
 * @author peiyue.xing
 */
@Component
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
