<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-22
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h3>로그인</h3>
  <form action="/member/login" method="post">
  <table border="1">
    <tr>
      <th>ID</th>
      <td><input type="text" name="id"></td>
    </tr>
    <tr>
      <th>PASSWORD</th>
      <td><input type="password" name="password"></td>
    </tr>
    <tr>
      <th>LOGIN</th>
      <td><input type="submit" value="로그인"></td>
    </tr>
  </table>
  </form>
  </body>
</html>
