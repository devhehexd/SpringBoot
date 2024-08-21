<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-21
  Time: 오후 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--태그 라이브러리 지시자--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>방명록</h3>
<a href="/book/add">글작성</a><br/>
<c:if test="${list.size() == 0}">
    작성된 글이 없습니다.
</c:if>
<c:if test="${list.size() != 0}">
    <table border="1">
        <tr>
            <th>글번호</th>
            <th>작성자</th>
        </tr>
        <c:forEach var="g" items="${list}">
            <tr>
                <td><a href="/book/detail?num=${g.num}">${g.num}</a></td>
                <td>${g.writer}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
