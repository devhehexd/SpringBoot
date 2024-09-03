package com.example.spring_security.authentication;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(HttpBasicConfigurer::disable) //기본 설정 disable
                .csrf(CsrfConfigurer::disable) //쓰기 작업 막은 것을 해제 (post, put.. 등을 요청 가능하게 설정)
                .cors(Customizer.withDefaults()) //네트워크 도메인, ip 허용 코드 작성한 내용을 허용
                .authorizeHttpRequests((authz) ->
                        //forward(서버 내 이동) 요청 모두 허용
                        authz.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/", "/join", "/autherror", "/loginform", "/login").permitAll()
                                .requestMatchers("/auth").authenticated())
                //로그인 폼 설정
                .formLogin((login) -> login
                        .loginPage("/loginform") //로그인 폼을 주는 요청 url
                        .loginProcessingUrl("/login") //로그인 처리 요청 url
                        .usernameParameter("id") //아이디 입력 양식의 이름
                        .passwordParameter("password") //패스워드 입력양식의 이름
                        .defaultSuccessUrl("/", true).permitAll() //성공 시 기본 이동 경로
                        .successHandler(new SuccessHandler()) //성공 시 실행될 핸들러
                        .failureHandler(new FailureHandler())); //실패 시 실행될 핸들러

        return httpSecurity.build();
    }
}
