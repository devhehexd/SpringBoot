<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3> 회원가입 폼 </h3>
	<form action="/member/join" method="post">
	id: <input type="text" name="id" > <br/>
	pwd: <input type="password" name="pwd" > <br/>
	<input type="submit" value="가입" > <br/>
	</form>
</body>
</html>