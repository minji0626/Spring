<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
<tiles:insertAttribute name="css" ignore="true"/>
</head>
<body>
	<div id="main">
		<div id="main_header">
			<!-- header의 내용이 들어옴 -->
			<tiles:insertAttribute name="header"/>
		</div>
		 <div id="main_body">
		 	<!-- body의 내용 -->
			<tiles:insertAttribute name="body"/> 
		 </div>
		 <div id="main_footer">
		 	<!-- footer의 내용 -->
			<tiles:insertAttribute name="footer"/> 
		 </div>
	</div>
</body>
</html>