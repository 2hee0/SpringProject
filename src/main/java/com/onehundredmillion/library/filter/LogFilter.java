package com.onehundredmillion.library.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString(); //요청 구분하기 위해 사용

        try {
            System.out.println("uuid: " + uuid);
            System.out.println("requestURI: "+ requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {

        } finally {
            System.out.println("uuid: " + uuid);
            System.out.println("requestURI: " + requestURI);

        }
    }

}