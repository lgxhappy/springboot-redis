package com.lgx.springbootredis.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/19 14:37
 */
public class MyHttpSessionListener implements HttpSessionListener {
    public static int online=0;
    //监听session的创建,synchronized 防并发bug
    public synchronized void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("【HttpSessionListener监听器】count++ 增加");
        online++;
        arg0.getSession().getServletContext().setAttribute("count", online);
    }
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("【HttpSessionListener监听器】count-- 减少");
        online--;
        arg0.getSession().getServletContext().setAttribute("count", online);
    }
}
