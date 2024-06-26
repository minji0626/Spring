<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 로그인 시작 -->
<div class="page-main">
	<h2>로그인</h2>
	<form:form action="login" id="member_login" modelAttribute="memberVO">
	<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li class="floating-label">
				<form:input path="id" placeholder="아이디" autocomplete="off" cssClass="form-input"/>
				<form:label path="id">아이디</form:label>
				<form:errors element="div" path="id" cssClass="error-color"/>
			</li>
			<li class="floating-label">
				<form:password path="passwd" placeholder="비밀번호" cssClass="form-input"/>
				<form:label path="passwd">비밀번호</form:label>
				<form:errors element="div" path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="auto"><input type="checkbox" name="auto" id="auto">자동 로그인</label>
			</li>
		</ul>
		<div>
			<form:button class="login-btn">로그인</form:button>
			
		</div>   
	</form:form>
	<p class="align-center login-btns">
		<input type="button" value="홈으로" class="default-btn"
			  onclick="location.href='${pageContext.request.contextPath}/main/main'">
		<input type="button" value="비밀번호 찾기" class="default-btn"
			  onclick="location.href='sendPassword'">
	</p>
</div>
<!-- 로그인 끝 -->