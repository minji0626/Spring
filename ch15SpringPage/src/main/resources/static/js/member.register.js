$(function(){
	/**
 	*  회원 가입
 	*/
	// 아이디 중복 여부 저장 변수
	let checkId = 0;
	// 0: 아이디 중복 또는 체크 미실행
	// 1: 아이디 미중복
	
	// 아이디 중복 체크
	 $('#confirmId').click(function(){
		if($('#id').val().trim() == ''){
			$('#message_id').css('color','red').text('아이디를 입력하세요');
			$('#id').val('').focus();
			return;
		}
		
		$('#message_id').text('');	// 알림 메세지 초기화
		
		// 서버와 통신하기
		// url: 같은 경로 url 명시
		$.ajax({
			url:'confirmId',
			type:'get',
			data:{id:$('#id').val()},
			dataType: 'json',
			success:function(param){
				if(param.result == `idDuplicated`){
					checkId = 0;
					$('#message_id').css('color','red').text('중복된 아이디');
					$('#id').val('').focus();
				}
				else if(param.result == `notMatchPattern`){
					checkId = 0;
					$('#message_id').css('color','red').text('잘못된 형식');
					$('#id').val('').focus();
				}
				else if(param.result == 'idNotFound'){
					checkId = 1;
					$('#message_id').css('color','#000').text('등록 가능한 ID');
				} else{
					checkId = 0;
					alert('ID 중복 체크 오류가 발생하였습니다.')
				}
			},
			error:function(){
				checkId = 0;
				alert('네트워크 오류가 발생하였습니다.');
			}
			
		}); // end of ajax
	 }); // end of click
	
	// 아이디 중복 안내 메세지 초기화 및 아이디 중복 값 초가화
	$('#member_register #id').keydown(function(){
		checkId = 0;
		$('#message_id').text('');
	}); // end of keydown
	
	$('#member_register').submit(function(){
		if(checkId == 0){
			$('#message_id').css('color','red').text('ID 중복 체크는 필수');
			
			if($('#id').val().trim()==''){
				$('#id').val('').focus();
			}
			return false;
		}
	});	// end of submit
	
	
}); // end of function

