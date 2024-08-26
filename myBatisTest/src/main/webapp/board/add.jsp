<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-23
  Time: 오후 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>글 작성</h3>
<form action="/board/add" method="post">
    <table border="1">
        <tr>
            <th>writer</th>
            <td><input type="text" name="writer" value="${sessionScope.loginId}" readonly></td>
        </tr>
        <tr>
            <th>title</th>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <th>content</th>
            <td><textarea rows="5" cols="30" name="content"></textarea></td>
        </tr>
        <tr>
            <th>작성</th>
            <td><input type="submit" value="작성"></td>
        </tr>
    </table>
</form>
</body>
</html>
