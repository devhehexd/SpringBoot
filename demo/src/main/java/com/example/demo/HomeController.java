package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//stereotype 클래스 종류 구별 , 클라이언트에 요청을 받아서 처리하는게 컨트롤러 
@Controller  // fullstack 컨트롤러 
public class HomeController {
	
	@GetMapping("/") // get 요청만 받음  / url 적음 / 루트에 오는 get 요청만 받겠다 / 그럼 하단의 컨트롤러 자동 실행 
	public String home() {  // 요청을 처리하는 뷰 페이지 경로를 반환 
		return "index";   //webapp 은 기본 루트라서 그 밑에 폴더만 적어줌 /views/index.jsp
	}
	
	//회원가입 폼
	@GetMapping("/member/join") 
	public String joinform() {
		return "joinFrom";
	}
	
	//회원가입 완룔를 하는 요청 
	@PostMapping("/member/join") // 회원가입 /member/join  -> form action 에 들어감 
	public String join() {  // 요청을 처리하는 뷰 페이지 경로를 반환 
		return "join";   // 회원가입 완료 
	}
	
	@GetMapping("/member/login") 
	public String login() {  // 요청을 처리하는 뷰 페이지 경로를 반환 
		return "login";  //로그인 폼   
	}
	
	@PostMapping("/member/login") 
	public String login2() {  // 요청을 처리하는 뷰 페이지 경로를 반환 
		return "loginres";  //로그인 폼   
	}
	
}
