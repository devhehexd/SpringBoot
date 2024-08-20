<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-20
  Time: 오후 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--태그 라이브러리 지시자--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>방명록 목록</h3>
<table border="1">
  <tr>
    <th>번호</th>
    <th>작성자</th>
    <th>작성일</th>
    <th>내용</th>
  </tr>
  <c:forEach var="c" items="${list}">
    <tr>
      <td><a href="/guestbook/detail?num=${c.num}">${c.num}</a></td>
      <td>${c.writer}</td>
      <td>${c.wdate}</td>
      <td>${c.content}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
