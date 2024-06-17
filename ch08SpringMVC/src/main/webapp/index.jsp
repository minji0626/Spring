<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/hello.do">Hello Controller</a>
<br>
<a href="${pageContext.request.contextPath}/search/internal.do">Search Controller(Internal)</a>
<br>
<a href="${pageContext.request.contextPath}/search/external.do?query=여름">Search Controller(External)</a>
<br>
<a href="${pageContext.request.contextPath}/article/newArticle.do">Article</a>
<br>
</body>
</html>