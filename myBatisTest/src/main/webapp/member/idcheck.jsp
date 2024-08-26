<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-22
  Time: 오후 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <script type="text/javascript">
    window.onload = () => { //onload: 윈도우(창)가 시작되자마자 함수 실행
      if ('${msg}' == '') {
        f.id.value = opener.document.f.id.value;
      }
      else {
        if (${flag}) {
          let div = document.getElementById("div");
          let html = "<input type='button' value='아이디 사용' onclick='b()'>";
          div.innerHTML = html;
        }
      }
    }

    const b = () => {
      opener.document.f.id.value = '${resultId}';
      close(); //현재 팝업창 닫음
    }
  </script>
</head>
<body>
<h3>중복체크</h3>
${msg}
<form action="/member/idcheck" method="post" name="f">
  id: <input type="text" name="id" value="${resultId}">
  <input type="button" value="중복체크" onclick="b()">
</form>
<div id="div"></div>
</body>
</html>
