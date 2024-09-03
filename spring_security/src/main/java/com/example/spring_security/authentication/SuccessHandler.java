package com.example.spring_security.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

//인증 성공 시 실행
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
        String loginId = authentication.getName();
        System.out.println("로그인 성공 / loginId: " + loginId);

        String path = "";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("구매자"))) {
            System.out.println("구매자");
            path = "/index_buyer";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("판매자"))) {
            System.out.println("판매자");
            path = "/index_seller";
        } else {
            System.out.println("권한 모름");
            path = "/";
        }
        //path 경로로 forward 방식으로 이동
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }
}
