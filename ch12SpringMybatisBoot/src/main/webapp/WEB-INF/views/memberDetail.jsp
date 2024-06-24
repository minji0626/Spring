<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>${membervo.name}</h2>
	<p>	
		회원 번호 : ${membervo.num}<br>
		회원 아이디 : ${membervo.id}<br>
		회원 전화번호 : ${membervo.phone}<br>
		회원 이메일 : ${membervo.email}<br>
		회원가입일 : ${membervo.reg_date}<br>
	</p>
	<hr width="100%" size="1" noshade="noshade">
	<div class="align-center">
		<input type="button" value="수정" 
		  onclick="location.href='memberUpdate.do?num=${membervo.num}'">
		<input type="button" value="삭제" 
		  onclick="location.href='memberDelete.do?num=${membervo.num}'">
		<input type="button" value="목록"
		  onclick="location.href='memberList.do'">    
	</div>
</div>
</body>
</html>