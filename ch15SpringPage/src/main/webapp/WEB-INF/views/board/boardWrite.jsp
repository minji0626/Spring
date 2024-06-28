<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 글 등록 시작 -->
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form action="write" id="board_register" enctype="multipart/form-data" modelAttribute="boardVO">
		<ul>
			<li>
				<form:label path="category">분류</form:label>
				<form:select path="category">
					<option disabled="disabled">선택하세요</option>
					<form:option value="1">자바</form:option>
					<form:option value="2">데이터베이스</form:option>
					<form:option value="3">자바스크립트</form:option>
					<form:option value="4">기타</form:option>
				</form:select>
				<form:errors element="div" path="category" cssClass="error-color-reg"/>
			</li>
			<li>
				<form:label path="title">제목</form:label>
				<form:input path="title" />
				<form:errors element="div" path="title" cssClass="error-color-reg"/>
			</li>
			<li>
				<form:label path="content">내용</form:label>
				<form:textarea path="content"/>
				<form:errors element="div" path="content" cssClass="error-color-reg"/>
			</li>
			<li>
				<form:label path="upload">파일 등록</form:label>
				<input type="file" id="upload" name="upload">
				<form:errors element="div" path="upload" cssClass="error-color-reg"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button class="default-btn">전송</form:button>
			<input type="button" value="목록" class="default-btn" onclick="location.href='list'">
		</div>   
	</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.register.js"></script>

</div>
<!-- 글 등록 종료 -->