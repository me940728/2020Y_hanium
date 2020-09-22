<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 등록</title>
</head>
<body>
<h3>새 글 등록</h3>
<form action="/board/doPost.do" method="post">
	<div>제목</div>
	<div><input type="text" name="post_title" style="width:500px; "required></div>
	<br>
	<div>내용</div>
	<div><textarea name="post_content" required style="width:500px;height:500px"></textarea></div>
	<div><input type="submit"></div>
	
</form>
</body>
</html>


