<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form:form action="login.do" modelAttribute="loginVO">
<!-- 필드가 없는 에러메시지를 처리하기 위해서 명시 -->
	<form:errors element="div" cssStyle="margin-bottom:10px; color:red; font-weight:bold;"/>
	아이디 : <form:input path="userId"/> <form:errors path="userId" cssStyle="color:red;"/>
	<br>
	비밀번호 : <form:password path="password"/> <form:errors path="password" cssStyle="color:red;"/>
	<br>
	<form:button>로그인</form:button>
</form:form>
</body>
</html>