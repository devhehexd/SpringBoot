<%@ taglib prefix="display" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: MZC-USER
  Date: 2024-08-22
  Time: 오후 5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <script type="text/javascript">
    const modify = () => {
      //const req = new XMLHttpRequest();

      const nameBox = document.getElementById("nameBox");
      nameBox.innerHTML = '<input type="text" id="nameInput" name="name" value="${member.name}">';

      const emailBox = document.getElementById("emailBox");
      emailBox.innerHTML = '<input type="text" id="emailInput" name="email" value="${member.email}">';

      const selectType = document.getElementById("selectType");
      selectType.innerHTML = '<select id="select" name="type"><option>구매자</option><option>판매자</option></select>';

      document.getElementById("submitBtn").style.display = "inline";
    }

    // const submit = () => {
    //
    //   const req = new XMLHttpRequest();
    //
    //   const nameValue = document.getElementById("nameInput").value;
    //   const emailValue = document.getElementById("emailInput").value;
    //   const selectValue = document.getElementById("selectType").value;
    //
    //   req.open("post", "/member/memberInfo");
    //   req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    //
    //   req.send();
    // }
  </script>
</head>
<body>
<h3>내 정보</h3>
<form action="/member/memberInfo" method="post">
  <input type="hidden" name="id" value="${sessionScope.loginId}">
  <input type="hidden" name="password" value="${member.password}">
<table border="1">
  <tr>
    <th>ID</th>
    <td>${member.id}</td>
  </tr>
  <tr>
    <th>NAME</th>
    <td id="nameBox">${member.name}</td>
  </tr>
  <tr>
    <th>EMAIL</th>
    <td id="emailBox">${member.email}</td>
  </tr>
  <tr>
    <th>TYPE</th>
    <td id="selectType">${member.type}</td>
  </tr>
  <tr>
    <th>
      <input type="button" value="수정" onclick="modify()">
      <input type="submit" id="submitBtn" value="완료" style="display:none;">
    </th>
  </tr>
</table>
</form>
</body>
</html>
