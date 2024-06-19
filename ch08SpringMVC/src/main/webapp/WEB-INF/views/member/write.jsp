<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form:form action="write.do" modelAttribute="command">
아이디:		<form:input path="id"/>
			<form:errors path="id" cssStyle="color:red; font-size:12px;"/>
			<br>
			
비밀번호:	<form:password path="password"/>
			<form:errors path="password" cssStyle="color:red; font-size:12px;"/>
			<br>
			
이름: 		<form:input path="name"/>
			<form:errors path="name" cssStyle="color:red; font-size:12px;"/>
			<br>
			
이메일: 	<form:input path="email"/>
			<form:errors path="email" cssStyle="color:red; font-size:12px;"/>
			<br>
			
나이:		<form:input path="age"/>
			<form:errors path="age" cssStyle="color:red; font-size:12px;"/>
			<br>
			
			<form:button>회원 가입</form:button>
	
</form:form>
</body>
</html>