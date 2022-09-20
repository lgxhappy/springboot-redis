package com.lgx.springbootredis.filter;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Lists;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.List;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/15 20:16
 */
@Log4j2
public class Myfilter implements Filter {
    //static final String TOKEN = "20220423344556abac";
    //内部接口集合
   // public static List<String> INSIDE_URLS = Lists.newArrayList("/index","/inside");
    //白名单接口集合
    //public static List<String> WHITE_PATH = Lists.newArrayList("/white","/login");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse)servletResponse);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
      if(requestURI.indexOf("/index")!=-1||requestURI.indexOf("/adminlogin")!=-1||requestURI.indexOf("/login")!=-1||requestURI.indexOf("/tologin")!=-1){
          //请求中包含/index和/asd就放行
          filterChain.doFilter(servletRequest,servletResponse);
      }else{
          //否则重定向道/login
          System.out.println("拦截请求"+requestURI);
          wrapper.sendRedirect("/login");
      }

    }

    @Override
    public void destroy() {
        log.info("过滤器销毁");
    }
}
