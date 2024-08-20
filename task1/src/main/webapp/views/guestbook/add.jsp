<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-20
  Time: 오후 6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>방명록 작성</h3>
<form action="/guestbook/add" method="post">
    작성자: <input type="text" name="writer"><br/>
    비밀번호: <input type="password" name="password"><br/><br/>
    방명록:<br/><textarea name="content" rows="10" cols="40" placeholder="100자 이내"></textarea><br/>
<input type="submit" value="작성">
</form>
</body>
</html>
