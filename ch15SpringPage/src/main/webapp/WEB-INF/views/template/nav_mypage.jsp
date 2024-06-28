<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- My page 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<!-- 사진의 정보를 저장한 곳에서 읽어오는 역할 -->
			<img src="${pageContext.request.contextPath}/member/photoView" width="200" height="200" class="my-photo">
			<div class="camera" id="photo_btn"><img src="${pageContext.request.contextPath}/images/camera.png" width="35"></div>
		</li>
		
		<li>
		<div id="photo_choice" style="display : none;">
			<input type="file" id="upload" accept="image/gif,image/png,image/jpeg"><br>
			<input type="button" value="전송" id="photo_submit">
			<input type="button" value="취소" id="photo_reset">
		</div>
		</li>
	</ul>
	
	<ul>
		<li>
		<input type="button" class="menu-btn" value="비밀번호 변경" onclick="location.href='changePassword'">
		</li>
		<li>
		<input type="button" class="menu-btn" value="회원 탈퇴" onclick="location.href='delete'">
		</li>
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.profile.js"></script>
	
</div>
<!-- My page 메뉴 끝 -->
