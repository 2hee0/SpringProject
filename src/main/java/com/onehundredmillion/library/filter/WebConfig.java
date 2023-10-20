/*
package com.onehundredmillion.library.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        //내가 방금 만든 로그 필터를 등록
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);// order를 정해서 순서 지정 가능
        filterRegistrationBean.addUrlPatterns("/*");    //모든 url에 적용

        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean  loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        //내가 방금 만든 로그 필터를 등록
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}*/