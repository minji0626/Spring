<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기 완료</title>
</head>
<body>
게시글이 등록되었습니다.
<br>
제목 : ${newArticleVO.title }<br>
작성자 : ${newArticleVO.name }<br>
내용 : ${newArticleVO.content }<br>
</body>
</html>