package com.example.sessiontest;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/test/login")
    public void loginForm() {

    }

    @PostMapping("/test/login")
    public String login(String id, String password, HttpSession session) {
        ///세션 정보 확인
        System.out.println("session id: " + session.getId());
        System.out.println("Creation Time: " + session.getCreationTime());
        System.out.println("session 마지막 접근: " + session.getLastAccessedTime());
        System.out.println("session 최대 시간: " + session.getMaxInactiveInterval());

        //로그인 처리 (ex. id: aaa, password: 111)
        String msg = "로그인 실패";
        if (id.equals("aaa") && password.equals("111")) {
            session.setAttribute("loginId", "aaa");
            session.setAttribute("type", "구매자");
            msg = "로그인 성공";
        }

        session.setAttribute("msg", msg);
        return "index";
    }

    @GetMapping("/test/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
