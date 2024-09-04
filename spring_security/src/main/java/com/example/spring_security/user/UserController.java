package com.example.spring_security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //구매자 페이지
    @RequestMapping("/index_buyer")
    public String index_buyer() {
        System.out.println("인증 테스트_buyer");
        return "index_buyer";
    }

    //판매자 페이지
    @RequestMapping("/index_seller")
    public String index_seller() {
        System.out.println("인증 테스트_seller");
        return "index_seller";
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
        return "error";
    }

    @RequestMapping("/auth/test")
    public String authTest() {
        System.out.println("인증 테스트");
        return "index_auth";
    }

    @RequestMapping("/auth/info")
    public String info(Model model) {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String loginId = authentication.getName(); //인증 유저 네임
        model.addAttribute("user", userService.getUser(loginId));
        return "member/info";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        //인증 객체 null로 처리
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }
}
