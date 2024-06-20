<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 보기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
    <h2> ${board.title}</h2>
    <p>
        글 번호 : ${board.num}<br>
        작성자 : ${board.writer}<br>
        작성일 : ${board.reg_date}<br>
    </p>
    <hr width="100%" size="1" noshade="noshade">
    <p>
    	${board.content}
    </p>
    <hr width="100%" size="1" noshade="noshade">
    <div class="align-right" id="detail_btn">
		<input type="button" value="수정" onclick="location.href='update.do?num=${board.num}'">
		<input type="button" value="삭제" onclick="location.href='delete.do?num=${board.num}'">
		<input type="button" value="목록" onclick="location.href='list.do'">
    </div>                   
</div>
</body>
</html>