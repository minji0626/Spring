$(function() {
	let rowCount = 10;
	let currentPage;
	let count;

	/*
	 *  댓글 목록 호출하기
	 */
	// 댓글 목록
	function selectList(pageNum) {
		currentPage = pageNum;
		
		//서버와 통신
		$.ajax({
			url: 'listReply',
			type: 'get',
			data: { board_num: $('#board_num').val(),pageNum,rowCount:rowCount },
			dataType: 'json',
			beforeSend: function(){
				//로딩 이미지 노출
				$('#loading').show();
			},
			complete : function(){
				//로딩 이미지 감추기
				$('#loading').hide();
			},
			success: function(param) {
				count = param.count;
				if (pageNum == 1) {
					$('#output').empty();
				}
				
				// 댓글 수 읽어 오기
				displayReplyCount(param);
				
				// 댓글 목록 작업
				$(param.list).each(function(index, item) {
					let output = '<div class="item">';
					output += '<ul class="detail-info">';
					output += '<li>';
					output += '<img src="../member/viewProfile?mem_num='+item.mem_num+'" width="40" height="40" class="my-photo">';
					output += '</li>';
					output += '<li>';
					
					if(item.nick_name){
						output += item.nick_name + '<br>';
					} else {
						output += item.id + '<br>';
					}
					
					if (item.re_mdate) {
						output += '<span class="modify-date">최근 수정일 | ' + item.re_mdate + '</span>';
					} else {
						output += '<span class="modify-date">등록일 | ' + item.re_date + '</span>';
					}
					
					output += '</li>';
					output += '</ul>';
					
					output += '<div class="sub-item">';
					output +='<p>' + item.re_content.replace(/\r\n/g,'<br>') + '</p>';
					
					// 좋아요 시작
					
					
					// 좋아요 끝
					
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if (param.user_num == item.mem_num) {
						output += ' <input type="button" data-renum="' + item.re_num + '" value="수정" class="modify-btn">';
						output += ' <input type="button" data-renum="' + item.re_num + '" value="삭제" class="delete-btn">';
					}
					
					
					// 답글 시작 
					
					// 답글 끝
					
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
				}); // end of each

				//page button 처리
				if (currentPage >= Math.ceil(count / rowCount)) {
					//다음 페이지가 없음
					$('.paging-button').hide();
				} else {
					//다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error: function() {
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
	}
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function() {
		selectList(currentPage + 1);
	});

	/*
	 *  댓글 등록
	 */
	// 댓글 등록
	$('#re_form').submit(function(event) {
		if ($('#re_content').val().trim() == '') {
			alert('내용을 입력하세요');
			$('#re_content').val('').focus();
			return false;
		}

		//form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링으로 반환
		let form_data = $(this).serialize();
		console.log(form_data);

		//서버와 통신
		$.ajax({
			url: 'writeReply',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인 후 작성');
				} else if (param.result == 'success') {
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을 포함해서 첫 번째
					//페이지의 게시글 목록을 다시 호출함
					selectList(1);
				} else {
					alert('댓글 등록 중 오류 발생');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});

		//기본 이벤트 제거
		event.preventDefault();
	});

	//댓글 작성 폼 초기화
	function initForm() {
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}


	/*
	 *  댓글 수정
	 */



	/*
	 *  댓글(답글) 등록, 수정 공통
	 */
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup', 'textarea', function() {
		//입력한 글자수 구함
		let inputLength = $(this).val().length;

		if (inputLength > 300) {//300자를 넘어선 경우
			$(this).val($(this).val().substring(0, 300));
		} else {//300자 이하인 경우
			let remain = 300 - inputLength;
			remain += '/300';
			if ($(this).attr('id') == 're_content') {
				//등록폼 글자수
				$('#re_first .letter-count').text(remain);
			} else if($(this).attr('id') == 'mre_content'){
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			} else if($(this).attr('id') == 'resp_content'){
				// 답글 폼
				$('#resp_first .letter-count').text(remain);
			} else {
				// 답글 수정폼
				$('#mresp_first .letter-count').text(remain);
			}
		}
	});




	/*
	 *  댓글 삭제
	 */




	/*
	 *  댓글 수 표시
	 */
	function displayReplyCount(param){
		
	}



	/*
	 *  댓글 좋아요 등록
	 */



	/*
	 *  댓글 좋아요 표시
	 */





	/*
	 *  답글 등록
	 */






	/*
	 *  답글 목록
	 */






	/*
	 *  답글 수정
	 */





	/*
	 *  답글 삭제
	 */





	/*
	 *  초기 데이터 호출
	 */
	selectList(1);


});