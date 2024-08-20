package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemService {
	@Autowired  //의존성을 자동으로 주입해라 (생상자 new 객체 안만들어도 됨 )
	private MemDao dao; 
	
	public void addMember() {  //기능
		dao.insert();
		System.out.println("service addmember");
	}
	
	public void getMember() {
		dao.select();
		System.out.println("service getMember");
	}
	
	public void getAll() {
		dao.selectAll();
		System.out.println("service getAll");
	}
	
	public void editMember() {
		dao.update();
		System.out.println("service editMember");
	}
	
	public void delMember() {
		dao.delete();
		System.out.println("service delMember");
	}
	
	
	
}
