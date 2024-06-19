<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<h2>게시판 목록</h2>
		<div class="align-right">
			<input type="button" value="글쓰기" onclick="location.href='insert.do'">
		</div>
	</div>
</body>
</html>