<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--태그 라이브러리 지시자--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>도시 추가</h3>
<form action="/city/add" method="post">
	name: <input type="text" name="name"> <br/>
	countryCode:
	<select name="countryCode">
	<c:forEach var="code" items="${list}"> <%--list에서 값을 꺼내서 code변수에 넣어라--%>
		<option>${code}</option>
	</c:forEach>
	</select> <br/>
	district: <input type="text" name="district"> <br/>
	population: <input type="number" name="population"> <br/>
	<input type="submit" value="추가">
</form>
</body>
</html>