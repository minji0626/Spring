<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 폼</title>
</head>
<body>
	<form:form action="submitReport.do" enctype="multipart/form-data" modelAttribute="report">
		<ul>
			<li>
				<form:label path="subject">제목</form:label>
				<form:input path="subject" cssStyle="margin-bottom:10px;"/> 
				<form:errors path="subject" cssStyle="color:red; font-weight:bold;"/>
			</li>
			<li>
				<form:label path="reportFile">파일</form:label>
				<input type="file" id="reportFile" name="reportFile"/> 
				<form:errors path="reportFile" cssStyle="color:red; font-weight:bold;"/>
			</li>
		</ul>
		<form:button>신고</form:button>
	</form:form>
</body>
</html>