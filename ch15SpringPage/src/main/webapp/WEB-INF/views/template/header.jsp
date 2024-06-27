<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  상단 시작 -->
<h2 class="align-center">Spring Page</h2>
<div class="align-right">
	<!-- 자바빈을 통채로 넣어 확인하기 -->
	<!-- 로그인이 되어있는 경우 -->
	<c:if test="${!empty user}">
		<a href="${pageContext.request.contextPath}/member/myPage" style="margin-right: 2%;">My Page</a>
		<img src="${pageContext.request.contextPath}/member/photoView" width="25" height="25" class="my-photo">
		<a href="${pageContext.request.contextPath}/member/myPage" style="margin-right: 2%;">${user.photo}</a>
		<a href="${pageContext.request.contextPath}/member/logout" style="margin-right: 2%;">로그아웃</a>
	</c:if>
	<!-- 로그인이 안 되어 있는 경우 -->
	<c:if test="${empty user}">
		<a href="${pageContext.request.contextPath}/member/registerUser" style="margin-right: 2%;">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login" style="margin-right: 2%;">로그인</a>
	</c:if>
		<a href="${pageContext.request.contextPath}/main/main" style="margin-right: 2%;">홈</a>

</div>
<!--  상단 끝 -->