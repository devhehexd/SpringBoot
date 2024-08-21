<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>hello spring</h3>
	${sessionScope.loginId} 로그인 중<br/>
	<a href="/mem/join">회원가입</a><br/>
	<a href="/member/login">로그인</a><br/>
	${m1.id} / ${m1.pwd } / ${m1.name }/ ${m1.email } <br/>
	<a href="/city/info"> 도시검색 </a> <br/>
	<a href="/city/add"> 도시추가 </a> <br/>  <%--=> 도시 추가하는 폼(city/add.jsp):get =>
	폼에 도시 정보 입력 submit => /city/add:post 요청 => 다시 index로--%>
	<a href="/city/list">도시목록</a>
</body> 
</html>