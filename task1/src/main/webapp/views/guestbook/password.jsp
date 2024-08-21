<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-20
  Time: 오후 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<c:if test="${msg != ''}">--%>
<%--    <p style="color:red;">${msg}</p>--%>
<%--</c:if>--%>
${msg}
<h3>비밀번호 확인</h3>
<form action="/guestbook/password" method="post">
    <input type="hidden" name="num" value="${gb.num}">
    <input type="hidden" name="action" value="${param.action}"> <!-- action 값을 전달 -->
    비밀번호: <input type="password" name="password" required><br/><br/>
    <input type="submit" value="확인">
</form>

</body>
</html>
