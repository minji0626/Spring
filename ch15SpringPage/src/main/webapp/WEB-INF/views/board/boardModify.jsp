<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 글 등록 시작 -->
<div class="page-main">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
	<h2>글 수정</h2>
	<form:form action="update" id="board_modify" enctype="multipart/form-data" modelAttribute="boardVO">
	<form:hidden path="board_num"/>
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
				<c:if test="${!empty boardVO.filename }">
					<div id="file_detail">
						(${boardVO.filename })파일이 등록되어 있습니다.
						<input type="button" id="file_del" value="파일 삭제">
					</div>
					<script type="text/javascript">
					$(function(){
						$('#file_del').click(function(){
							const choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile',
									data:{board_num:${boardVO.board_num}},
									type: 'post',
									dataType:'json',
									success:function(param){
										if(param.result == 'wrongAccess'){
											alert('접근 권한이 없습니다.')
										} else if(param.result == 'success'){
											$('#file_detail').hide();
										} else if(param.result == 'logout'){
											alert('로그인 후 사용하세요.');
										} else {
											alert('파일 삭제 중 오류가 발생하였습니다.')
										}
									},
									error:function(){
										alert('네트워크 오류가 발생하였습니다.')
									}
								})
							}
						});
					});
					</script>
				</c:if>
				<form:errors element="div" path="upload" cssClass="error-color-reg"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button class="default-btn">전송</form:button>
			<input type="button" value="목록" class="default-btn" onclick="location.href='list'">
		</div>   
	</form:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.register.js"></script>

</div>
<!-- 글 등록 종료 -->