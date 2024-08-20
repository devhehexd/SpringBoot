<%--
  Created by IntelliJ IDEA.
  User: rlqja
  Date: 2024-08-21
  Time: 오전 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>방명록 수정</h3>
<form action="/guestbook/edit" method="post">
  <input type="hidden" name="num" value="${gb.num}">
  작성자: ${gb.writer}<br/>
  작성일: ${gb.wdate}<br/>
  방명록:<br/><textarea name="content" rows="10" cols="40">${gb.content}</textarea><br/>
  <input type="submit" value="저장">
</form>
</body>
</html>
