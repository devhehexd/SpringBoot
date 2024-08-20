package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mem")  //get,post 상관없이 헤딩 컨트롤러의 공통 url, 둥록하는 모든 url 앞에 자동으로 mem이 붙는것. 
public class MemController {
	@Autowired  // 객체 자동으로 넣기 
	private MemService service;
	
	@GetMapping("/join")   ///mem/join
	public String joinFrom() {
		return"/member/join";  //  /views/member/join 
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute("m1")Member m) {  //@ModelAttribute 뷰페이지에서 부를 이름 "m1" 
		System.out.println(m);
		service.addMember();
		return "index";  // 응답페이지로 자동 이동 
	}
	
	@GetMapping("/get")
	public String get() {
		service.getMember();
		return"/member/info";
	}
	
	@GetMapping("/getall")
	public String getall() {
		service.getAll();
		return"/member/getall";
	}
	
	@GetMapping("/edit")
	public String edit() {
		service.editMember();
		return"/member/edit";
	}
	
	@GetMapping("/del")
	public String del() {
		service.delMember();
		return"/member/del";
	}
	
	//숙제 
	//검색. url:get. view:info.jsp
	
	//전체검색  url:getall. view:getall.jsp
	
	//수정 url:edit. view:edit.jsp
	
	//삭제 url:get. view:del.jsp
	
	
}
