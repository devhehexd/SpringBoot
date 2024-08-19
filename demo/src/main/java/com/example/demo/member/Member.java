package com.example.demo.member;

//vo
public class Member {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	
	public Member() { //내가 사용하지 않아도 framework에서 사용하기 때문에 기본 생성자는 꼭 만들어 줘야한다.
	}
	
	public Member(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	

}
