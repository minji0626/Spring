<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>회원 정보 수정</h2>
	<form:form action="memberUpdate.do" modelAttribute="member">
		<form:hidden path="num"/>
		<ul>
			<li>
				<form:label path="name">이름</form:label>
				<form:input path="name"/>
				<form:errors element="div" path="name" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="id">아이디</form:label>
				<form:input path="id"/>
				<form:errors element="div" path="id" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="passwd">비밀번호</form:label>
				<form:password path="passwd"/>
				<form:errors element="div" path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="phone">전화 번호</form:label>
				<form:input path="phone" placeholder="010********" />
				<form:errors element="div" path="phone" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="email">이메일</form:label>
				<form:input path="email"/>
				<form:errors element="div" path="email" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
			<input type="button" value="목록"
			   onclick="location.href='memberList.do'">
		</div>
	</form:form>
</div>
</body>
</html>