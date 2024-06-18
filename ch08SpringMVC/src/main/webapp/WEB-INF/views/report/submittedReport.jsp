<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 완료</title>
</head>
<body>
<b>신고가 완료되었습니다.</b><br>
제목 : ${report.subject } <br>
파일 : ${report.reportFile.originalFilename }<br>
용량 : ${report.reportFile.size }
</body>
</html>