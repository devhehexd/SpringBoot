package com.example.spring_security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/";
    }

    @GetMapping("/loginform")
    public String loginForm() {
        return "member/login";
    }

    @RequestMapping("/autherror")
    public String authError() {
        return "autherror";
    }

    @RequestMapping("/auth/test")
    public String authTest() {
        System.out.println("인증 테스트");
        return "index_auth";
    }
}
