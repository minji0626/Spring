<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>${board.title}</h2>
	<p>	
		글번호 : ${board.num}<br>
		작성자 : ${board.writer}<br>
		작성일 : ${board.reg_date}<br>
	</p>
	<hr width="90%" size="1" noshade="noshade">
	<p>
		${board.content}
	</p>
	<div class="align-center">
		<input type="button" value="수정" 
		  onclick="location.href='update.do?num=${board.num}'">
		<input type="button" value="삭제" 
		  onclick="location.href='delete.do?num=${board.num}'">
		<input type="button" value="목록"
		  onclick="location.href='list.do'">    
	</div>
</div>
</body>
</html>






