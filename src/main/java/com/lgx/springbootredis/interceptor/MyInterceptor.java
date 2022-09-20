package com.lgx.springbootredis.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/19 14:41
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    //再处理请求前进行调用（controller方法调用之前）
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println("preHandle被调用11");
        //springmvc在拦截其中获得rest风格的变量
        Object loginuser = httpServletRequest.getSession().getAttribute("loginuser");
        if(loginuser!=null) {
            return true;    //如果false，停止流程，api被拦截
        }else {
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write("please login again!");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
