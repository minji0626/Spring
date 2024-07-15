<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 채팅 메시지 처리 시작 -->
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/message.talk.js"></script>
<div id="talkDetail" class="page-main">
	<h1 id="chatroom_title"><span id="chatroom_name">${room_name}</span> 채팅방
		<input type="button" value="채팅방이름 변경" id="change_name">
	</h1> 
	<div class="align-right">
		<input type="button" value="멤버추가" id="opener">
	    <input type="button" value="방탈출" id="delete_talkroom">
	    <input type="button" value="목록" onclick="location.href='talkList'">
	</div>    
	<p>
		채팅 멤버 : 
		<span id="chat_member">${chatMember}</span><span id="chat_mcount">(${chatCount}명)</span>
	</p>    
	<div id="chatting_message"></div>
	<form id="detail_form">
		<input type="hidden" name="talkroom_num" id="talkroom_num" value="${param.talkroom_num}">	
	    <textarea rows="5" cols="40" name="message" id="message"></textarea>
		<div id="message_btn">
			<input type="submit" value="전송">
		</div>
	</form>
</div>
<!-- 채팅 메시지 처리 끝 -->







