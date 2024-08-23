<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-23
  Time: 오전 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>쿠키 테스트</h3>
${cookie.num.name} / ${cookie.num.value}<br/>
${cookie.name.name} / ${cookie.name.value}<br/>
${cookie.JSESSIONID.name} / ${cookie.JSESSIONID.value}<br/>
<a href="/cookie/add?num=1&name=aaa">쿠키추가</a>
<a href="/cookie/list">쿠키목록</a>
</body>
</html>
