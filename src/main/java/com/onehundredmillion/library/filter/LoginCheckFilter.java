package com.onehundredmillion.library.filter;

import com.onehundredmillion.library.sessioin.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "/join", "/login", "/logout", "/css/*", "/js/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            System.out.println("인증 체크 필터 시작 : " + requestURI);
            if (isLoginCheckPath(requestURI)) {
                //whitelist에 없다면(true)
                System.out.println("인증 체크 로직 실행: " + requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null ||
                        session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    System.out.println("미인증 사용자 요청:" + requestURI);

                    //로그인으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("인증 체크 필터 종료 : " + requestURI);
        }
    }


    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);

    }
}