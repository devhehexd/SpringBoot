<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-23
  Time: 오후 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--태그 라이브러리 지시자--%>
<html>
<head>
    <title>Title</title>
  <script type="text/javascript">
    const a = (num) => {
      const req = new XMLHttpRequest();

      req.onload = () => {
        let res = document.getElementById("res")
        let obj = JSON.parse(req.responseText); //JSON 객체로 변환
        let txt = "글 상세내용<br/>";
        txt += "num: " + obj.num + "<br/>";
        txt += "writer: " + obj.writer + "<br/>";
        txt += "wdate: " + obj.wdate + "<br/>";
        txt += "title: " + obj.title + "<br/>";
        txt += "content: " + obj.content + "<br/>";
        res.innerHTML = txt;
      }

      req.open('get', '/board/getAjax?num=' + num);
      req.send();
    }

    const b = () => {
      let res = document.getElementById("res");
      res.innerHTML = '';
    }
  </script>
</head>
<body>
<h3>게시판</h3>
<span id="res" style="position: absolute; top: 200px; left: 300px"></span>
<a href="">오늘 읽은 글 목록</a><br/>
<a href="/board/add">글 작성</a><br/>
<select name="gettype">
  <option>작성자</option>
  <option>제목</option>
</select>
<input type="text" name="search"/>
<input type="button" value="검색">
<table border="1">
  <tr>
    <th>num</th><th>title</th><th>writer</th>
  </tr>
  <c:forEach var="b" items="${list}">
    <tr>
      <td><a href="/board/detail?num=${b.num}">${b.num}</a></td>
      <td><span onmouseover="a(${b.num})" onmouseout="b()">${b.title}</span></td>
      <td>${b.writer}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
