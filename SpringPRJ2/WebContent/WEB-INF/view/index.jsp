<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function innerHTMLTest(){
	var randValNode = document.getElementById("rand-val");
	randValNode.innerHTML = "<b><font color='red'>"+Math.random()+"</font></b>";
}
</script>
<title>Insert title here</title>
<style>
	table, th, td{
		border : 1px solid black;
	}
</style>
</head>
<body>
	<div>
		<!-- Top Start-->
		<%@ include file="/WEB-INF/view/user/top.jsp"%>
		<!-- Top END-->
	</div>
	
	<div id="rand-val">랜덤 값 시작	</div>  
	<button onclick="innerHTMLTest()">생성</button>

</body>
</html>