<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Board List 시작 -->
<div class="page-main">
 	<h2>게시판 목록</h2>
 	<div class="align-right">
 		<input type="button" value="게시글 등록" onclick="location.href='write'" 
 		<c:if test="${empty user}"> disabled="disabled" </c:if>>
 	</div>
 	
</div>
<!-- Board List 종료 -->