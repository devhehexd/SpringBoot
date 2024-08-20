<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-20
  Time: 오후 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<script type="text/javascript">
  const a = () => {
    location.href = "/guestbook/delete?id=${gb.num}"
  }
</script>
</head>
<body>
<h3>상세 페이지</h3>
  ${msg}<br/>
  번호: ${gb.num}<br/>
  작성자: ${gb.writer}<br/>
  작성일: ${gb.wdate}<br/>
  내용: ${gb.content}<br/>
<form action="/guestbook/password" method="post">
  <input type="hidden" name="num" value="${gb.num}">
  <input type="hidden" name="action" value="edit">
  <button type="submit">수정</button>
</form>
<form action="/guestbook/password" method="post">
  <input type="hidden" name="num" value="${gb.num}">
  <input type="hidden" name="action" value="delete">
  <button type="submit">삭제</button>
</form>
</body>
</html>
