<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
		<input type="button" value="글쓰기"
		    onclick="location.href='insert.do'">
		<input type="button" value="회원 목록"
		    onclick="location.href='memberList.do'">
	</div>
	<c:if test="${count == 0 }">
	<div class="result-display">표시할 내용이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.num}</td>
			<td><a href="detail.do?num=${board.num}">${board.title}</a></td>
			<td>${board.writer}</td>
			<td>${board.reg_date}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
</body>
</html>






