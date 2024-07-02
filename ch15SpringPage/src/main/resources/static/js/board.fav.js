/**
 * 
 */
$(function(){
	
	/*
	좋아요 읽기 (좋아요 선택 여부와 선택한 총 개수 표시하기)
	*/
	function selectFav(board_num){
		// 서버와 통신
		$.ajax({
			url:'getFav',
			type:'get',
			data: {board_num : board_num},
			dataType:'json',
			success:function(param){
				displayFav(param);
			},
			error:function(){
				alert('네트워크 오류가 발생하였습니다.')
			}
		})
	}
	
	/*
	좋아요 표시 공통 함수
	*/
	
	function displayFav(param) {
		let output;
		if(param.status == 'yesFav'){
			output = '../images/fav02.gif';
		} else if (param.status == 'noFav'){
			output = '../images/fav01.gif';
		} else {
			alert('좋아요 표시 오류 발생');
		}
		
		// 문서 객체에 추가
		$('#output_fav').attr('src',output);
		$('#output_fcount').text(param.count);
	}
	
	
	/*좋아요 등록 삭제*/
	$('#output_fav').click(function(){
		// 서버랑 통신하기
		$.ajax({
			url:'writeFav',
			type:'post',
			data: {board_num : $('#output_fav').attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.result == 'success'){
						displayFav(param);
				} else if(param.result == 'logout'){
					alert('로그인 후 사용 가능합니다.');
				} else {
					alert('좋아요 처리 중 오류가 발생하였습니다.')
				}
			},
			error:function(){
				alert('네트워크 오류가 발생하였습니다.')
			}
		})
	})
	
	/*초기 데이터 호출하기*/
	selectFav($('#output_fav').attr('data-num'));
	
	
})