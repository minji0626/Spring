<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 글 등록 시작 -->
<div class="page-main">
	<h2>회원 가입</h2>
	<form:form action="registerUser" id="member_register" modelAttribute="memberVO">
		<ul>
			<li>
				<form:label path="id"></form:label>
				<form:input path="id" />
				<form:errors element="div" path="id" cssClass="error-color-reg"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button class="default-btn">전송</form:button>
			<input type="button" value="홈으로"
			  class="default-btn"
			  onclick="location.href='${pageContext.request.contextPath}/main/main'">
		</div>   
	</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.register.js"></script>

</div>
<!-- 글 등록 종료 -->