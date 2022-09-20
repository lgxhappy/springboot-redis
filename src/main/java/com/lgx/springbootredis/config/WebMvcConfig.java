package com.lgx.springbootredis.config;
import com.lgx.springbootredis.interceptor.MyInterceptor;
import com.lgx.springbootredis.listener.MyHttpSessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/15 20:32
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/asd/**");
    }

    @Bean
    //注册过滤器
    public FilterRegistrationBean filterRegist() {
        //创建字符集编码过滤器
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        //设置强制使用指定的字符集编码
        characterEncodingFilter.setForceEncoding(true);
        //设置指定的字符集编码
        characterEncodingFilter.setEncoding("utf-8");

        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        //设置字符集编码过滤器
        filterRegistrationBean.setFilter(characterEncodingFilter);
        //设置字符集编码过滤器的路径
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
       /* FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new Myfilter());
        frBean.addUrlPatterns("/*");
        System.out.println("filter");
        return frBean;*/
    }

    @Bean
    //注册监听器
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        System.out.println("listener");
        return srb;
    }

}
