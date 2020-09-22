<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

</style>
</head>
<body>
	<table border='1'>
	<%for(int row=0; row<3; row++) {%>
		<tr>
		<%for(int col=0; col<4; col++){ %>
			<td><%=row %>행 <%=col %>열</td>
		<%} %>
		</tr>
	<%} %>
	</table>
</body>
</html>
