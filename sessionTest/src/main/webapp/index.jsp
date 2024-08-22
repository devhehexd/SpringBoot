<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-22
  Time: 오전 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.loginId == null}">
    ${msg}<br/>
    <a href="/member/join">회원가입</a><br/>
    <a href="/member/login">로그인</a>
</c:if>
<c:if test="${sessionScope.loginId != null}">
    ${sessionScope.loginId}님의 타입은 ${sessionScope.type} 입니다.<br/>
    <a href="/member/logout">로그아웃</a>
    <a href="/member/memberInfo">내 정보확인</a>
    <a href="/member/login">탈퇴</a>
    <a href="/member/login">게시판</a>
</c:if>

</body>
</html>
