<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-23
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <script type="text/javascript">
    const d = () => {
      location.href = "/board/delete?num=${board.num}"
    }
  </script>
</head>
<body>
<h3>상세 페이지</h3>
<c:if test="${!sessionScope.loginId.equals(board.writer)}">
  <c:set var="str">readonly</c:set> <!--let str = "readonly"-->
</c:if>
<form action="/board/edit" method="post">
  <table border="1">
    <tr>
      <th>num</th>
      <td><input type="text" name="num" value="${board.num}" readonly></td>
    </tr>
    <tr>
      <th>writer</th>
      <td><input type="text" value="${board.writer}" readonly></td>
    </tr>
    <tr>
      <th>wdate</th>
      <td><input type="text" value="${board.wdate}" readonly></td>
    </tr>
    <tr>
      <th>title</th>
      <td><input type="text" name="title" value="${board.title}" ${str}></td>
    </tr>
    <tr>
      <th>content</th>
      <td><textarea rows="5" cols="30" name="content" ${str}>${board.content}</textarea></td>
    </tr>
    <tr>
      <c:if test="${sessionScope.loginId.equals(board.writer)}">
        <th>편집</th>
        <td>
          <input type="submit" value="수정">
          <input type="button" value="삭제" onclick="d()">
        </td>
      </c:if>
    </tr>
  </table>
</form>
</body>
</html>
