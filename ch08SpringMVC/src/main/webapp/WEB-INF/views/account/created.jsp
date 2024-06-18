<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 완료</title>
</head>
<body>
회원 가입이 완료되었습니다
<ul>
	<li>아이디 : ${command.id }</li>
	<li>이름 : ${command.name }</li>
	<li>우편번호 : ${command.zipcode }</li>
	<li>주소 : ${command.address1 } ${command.address2 }</li>
</ul>
</body>
</html>