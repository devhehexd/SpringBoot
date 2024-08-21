<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-21
  Time: 오후 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>방명록 작성</h3>
<form action="/book/add" method="post">
<table border="1">
    <tr>
        <th>작성자</th>
        <td><input type="text" name="writer"></td>
    </tr>
    <tr>
        <th>글비밀번호</th>
        <td><input type="password" name="password"></td>
    </tr>
    <tr>
        <th>내용</th>
        <td><input type="text" name="content"></td>
    </tr>
    <tr>
        <th>작성</th>
        <td><input type="submit" value="작성"></td>
    </tr>
</table>
</form>
</body>
</html>
