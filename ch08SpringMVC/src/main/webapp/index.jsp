<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<style type="text/css">
a{
	text-decoration : none;
	color: black;
	font-weight:bold;
}
</style>
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

<a href="${pageContext.request.contextPath}/cookie/make.do">CookieController - make.do</a>
<br>

<a href="${pageContext.request.contextPath}/cookie/view.do">CookieController - view.do</a>
<br>

<a href="${pageContext.request.contextPath}/search/main.do">Game Search Controller</a>
<br>

<a href="${pageContext.request.contextPath}/account/create.do">New Account</a>
<br>

<a href="${pageContext.request.contextPath}/login/login.do">Login</a>
<br>

<a href="${pageContext.request.contextPath}/report/submitReport.do">File Upload</a>
<br>

<a href="${pageContext.request.contextPath}/member/write.do">MemberWriteController</a>
<br>

<a href="${pageContext.request.contextPath}/file.do">Download file</a>
<br>


</body>
</html>