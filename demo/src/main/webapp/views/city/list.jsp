<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-20
  Time: 오후 4:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--태그 라이브러리 지시자--%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h3>도시 목록</h3>
  <table border="1">
    <tr>
      <th>id</th>
      <th>name</th>
      <th>countryCode</th>
      <th>district</th>
      <th>population</th>
    </tr>
    <c:forEach var="c" items="${list}">
      <tr>
        <td>${c.id}</td>
        <td><a href="/city/detail?id=${c.id}">${c.name}</a></td> <%--get방식이므로 url에 parameter로 id를 받을 수 있다--%>
        <td>${c.countryCode}</td>
        <td>${c.district}</td>
        <td>${c.population}</td>
      </tr>
    </c:forEach>
  </table>
  </body>
</html>
