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
    location.href = "/guestbook/detail?id=${gb.num}"
  }
</script>
</head>
<body>
<h3>상세 페이지</h3>
<form action="guestbook/edit" method="post">
  ${msg}<br/>
  번호: ${gb.num}<br/>
  작성자: ${gb.writer}<br/>
  작성일: ${gb.wdate}<br/>
  내용: ${gb.content}<br/>
  <input type="submit" value="수정">
  <input type="button" value="삭제">
</form>
</body>
</html>
