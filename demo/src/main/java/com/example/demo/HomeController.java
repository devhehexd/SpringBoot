package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//stereotype: 클래스 종류
//Controller: 클라이언트의 요청을 받아서 처리
@Controller //Fullstack Controller
public class HomeController {
	
	@GetMapping("/") //get 요청만 받음
	    public String home() { //요청을 처리하는 메서드는 뷰 페이지 경로를 반환
	        return "index";
	    }
	
	//회원가입 폼
	@GetMapping("/member/join") //get 요청만 받음
    public String joinform() { //요청을 처리하는 메서드는 뷰 페이지 경로를 반환
        return "joinForm"; //회원가입 완료 메세지 출력
    }
	
	//회원가입 완료
	@PostMapping("/member/join") //post 요청만 받음
    public String join() { //요청을 처리하는 메서드는 뷰 페이지 경로를 반환
        return "join"; //회원가입 완료 메세지 출력
    }
	
	@GetMapping("/member/login") //get 요청만 받음
    public String login() { //요청을 처리하는 메서드는 뷰 페이지 경로를 반환
        return "login"; //로그인 폼
    }
	
	@PostMapping("/member/login") //post 요청만 받음
    public String loginres() { //요청을 처리하는 메서드는 뷰 페이지 경로를 반환
        return "loginRes"; //로그인 폼
    }

}
