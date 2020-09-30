<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="poly.util.CmmUtil" %>
    <%@ page import="poly.dto.UserInfoDTO" %>
    <%
    UserInfoDTO uDTO = (UserInfoDTO)request.getAttribute("uDTO");
    
    if(uDTO == null){
       uDTO = new UserInfoDTO();
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일</title>
</head>
<body>
<h1>이메일 찾기</h1>
가입하신 이메일은 <%=CmmUtil.nvl(uDTO.getEmail()) %> 입니다.
<br>
<br>
<hr>
<a href="/user/userLogin.do">로그인 화면으로 돌아가기</a>
</body>
</html>