package com.example.sessiontest.ajaxtest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie")
public class CookieTestController {

    @GetMapping("/add")
    public String add(int num, String name, HttpServletResponse response) {
        Cookie cookie1 = new Cookie("num", num + "");
        Cookie cookie2 = new Cookie("name", name);

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "cookie/index";
    }

    @GetMapping("/list")
    public String list(HttpServletRequest request) {
        Cookie[] list = request.getCookies();
        for (Cookie cookie : list) {
            System.out.println("name: " + cookie.getName() + ", value: " + cookie.getValue());
        }
        return "cookie/index";
    }
}
