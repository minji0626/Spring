<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<form:form action="create.do" modelAttribute="command">
	아이디 : <form:input path="id"/>
			 <form:errors path="id"/>
			 <br>
			 
	이름: <form:input path="name"/>
		  <form:errors path="name"/>
		  <br>
		  
	우편번호 : <form:input path="zipcode"/>
			   <form:errors path="zipcode"/>
			   <br>
			   
	주소 : <form:input path="address1"/>
		   <form:errors path="address1"/>
		   <br>
		   
	상세 주소 : <form:input path="address2"/>
				<form:errors path="address2"/>
				<br>

	<form:button>전송</form:button>
</form:form>
</body>
</html>