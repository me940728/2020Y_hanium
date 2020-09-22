<%@page import="poly.dto.BoardDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	BoardDTO rDTO = (BoardDTO)request.getAttribute("rDTO");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=nvl(rDTO.getPost_title()) %></title>
</head>
<body>
<div style="width:550px;">
	<h4><%=nvl(rDTO.getPost_title()) %></h4>
	
	<hr>
	<div style="text-align:right;">작성자 : <%=nvl(rDTO.getReg_id()) %></div>
	<div>
		<p><%=nvl(rDTO.getPost_content()) %></p>
	</div>
	<hr>
	<div style="float:left;">
		<button onclick="location.href='/board/boardList.do'">뒤로</button>
	</div>
	<div style="float:right;">
	<button onclick="confirmDelete();">삭제</button>
	<button onclick="location.href='/board/editPost.do?no=<%=rDTO.getPost_no()%>'">편집</button>
	</div>
</div>
<script type="text/javascript">
function confirmDelete(){
	if(confirm("삭제하시겠습니까?")){
		location.href='/board/deletePost.do?no=<%=rDTO.getPost_no()%>';
	}
}

</script>
</body>
</html>


