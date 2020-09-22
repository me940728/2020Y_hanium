<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	BoardDTO rDTO = (BoardDTO)request.getAttribute("rDTO");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 편집</title>
</head>
<body>
<h3>새 글 등록</h3>
<form action="/board/doEditPost.do" method="post">
	<div>제목</div>
	<div><input type="text" value="<%=nvl(rDTO.getPost_title()) %>" name="post_title" style="width:500px;" required></div>
	<br>
	<div>내용</div>
	<div><textarea name="post_content" required style="width:500px;height:500px"><%=nvl(rDTO.getPost_content()) %></textarea></div>
	<input name="post_no" value="<%=nvl(rDTO.getPost_no()) %>" hidden="hidden">
	<div><input type="submit" value="편집"></div>
</form>
</body>
</html>


