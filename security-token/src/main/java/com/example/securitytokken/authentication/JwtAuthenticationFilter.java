package com.example.securitytokken.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.security.Security;

//필터 클래스 추가
//요청이 올 때마다 요청 헤더에서 토큰을 꺼내 유효성 체크
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response2 = (HttpServletResponse) response;
        response2.setHeader("Access-Control-Allow-Origin", "*");
        response2.setHeader("Access-Control-Allow-Credentials", "true");
        response2.setHeader("Access-Control-Allow-Methods", "*");
        response2.setHeader("Access-Control-Allow-Max-Age", "3600");
        response2.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, " +
                "Content-Type, Accept, Authorization");
        response2.setStatus(HttpServletResponse.SC_OK);

        String token = tokenProvider.resolveToken((HttpServletRequest) request);
        //헤더에 토큰이 있고 토큰의 파기 시간이 남아있으면 정상
        if (token != null && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //연결된 다음 필터 실행
        chain.doFilter(request, response);

    }
}
