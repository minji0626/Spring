<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main">
	<h2>비밀번호 변경</h2>
	<form:form action="changePassword" id="member_change" modelAttribute="memberVO">
		<ul>
			<li>
				<form:label path="now_passwd">현재 비밀번호</form:label>
				<form:password path="now_passwd" />
				<form:errors element="div" path="now_passwd" cssClass="error-color-reg"/>
			</li>
			<li>
				<form:label path="passwd">새 비밀번호</form:label>
				<form:password path="passwd" />
				<form:errors element="div" path="passwd" cssClass="error-color-reg"/>
			</li>
			<li>
				<label for="confirm_passwd">새 비밀번호 확인</label>
				<input type="password" id="confirm_passwd">
				<div id="message_password"></div>
			</li>
			<li>
				<div id="captcha_div">
					<img src="getCaptcha" id="captcha_img" width="200" height="90">
				</div>
				<input type="button" value="새로고침" id="reload_btn">
				<script type="text/javascript">
					$(function(){
						$('#reload_btn').click(function(){
							$.ajax({
								url:'getCaptcha',
								type:'get',
								success:function(){
									$('#captcha_div').load(location.href+' #captcha_div');
								},
								error:function(){
									alert('네트워크 오류가 발생하였습니다.');
								}
							});
						});
					});
				</script>
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.password.js"></script>
			</li>
			<li>
				<form:label path="captcha_chars">문자 확인</form:label>
				<form:input path="captcha_chars" />
				<form:errors element="div" path="captcha_chars" cssClass="error-color-reg"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button class="default-btn">수정</form:button>
			<input type="button" value="MyPage" class="default-btn" onclick="location.href='myPage'">
		</div>   
	</form:form>
</div>