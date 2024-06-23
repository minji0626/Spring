<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>회원 목록</h2>
	<div class="align-right">
		<input type="button" value="회원가입"
		    onclick="location.href='newAccount.do'">
		<input type="button" value="글 목록"
			   onclick="location.href='list.do'">
	</div>
	<c:if test="${count == 0 }">
	<div class="result-display">표시할 내용이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th>회원 아이디</th>
			<th>회원 이름</th>
			<th>가입일</th>
		</tr>
		<c:forEach var="member" items="${list}">
		<tr>
			<td>${member.num}</td>
			<td><a href="memberDetail.do?num=${member.num}">${member.id}</a></td>
			<td>${member.name}</td>
			<td>${member.reg_date}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
</body>
</html>