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
			data: { board_num: $('#board_num').val(), pageNum, rowCount: rowCount },
			dataType: 'json',
			beforeSend: function() {
				//로딩 이미지 노출
				$('#loading').show();
			},
			complete: function() {
				//로딩 이미지 감추기
				$('#loading').hide();
			},
			success: function(param) {
				count = param.count;
				if (pageNum == 1) {
					$('#output').empty();
				}

				// 댓글 수 읽어 오기
				displayReplyCount(param.count);

				// 댓글 목록 작업
				$(param.list).each(function(index, item) {
					// 댓글 좋아요
					let fav_cnt = 0;
					if(item.refav_cnt!=0){
						fav_cnt = item.refav_cnt;
					}
					// 댓글 좋아요
					let output = '<div class="item">';
					output += '<ul class="detail-info">';
					output += '<li>';
					output += '<img src="../member/viewProfile?mem_num=' + item.mem_num + '" width="40" height="40" class="my-photo">';
					output += '</li>';
					output += '<li>';

					if (item.nick_name) {
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
					output += '<p>' + item.re_content.replace(/\r\n/g, '<br>') + '</p>';

					// 좋아요 시작
					if(item.click_num == 0 || param.user_num!=item.click_num){
						output+= '<img class="output_rfav" src="../images/heart01.png" data-num="'+item.re_num+'"> <span class="output_rfcount">'+fav_cnt+'</span>';
					}else{
						output+= '<img class="output_rfav" src="../images/heart02.png" data-num="'+item.re_num+'"> <span class="output_rfcount">'+fav_cnt+'</span>';
					}

					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if (param.user_num == item.mem_num) {
						output += ' <input type="button" data-num="' + item.re_num + '" value="수정" class="modify-btn">';
						output += ' <input type="button" data-num="' + item.re_num + '" value="삭제" class="delete-btn">';
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


	/*  댓글 수정 버튼 클릭시 수정폼을 노출시킨다.
	 *  댓글 수정
	 */
	$(document).on('click', '.modify-btn', function() {
		//
		let re_num = $(this).attr('09data-num');
		let re_content = $(this).parent().find('p').html().replace(/<br>/gi, '\r\n');

		let modifyUI = '<form id="mre_form">';
		modifyUI += '<input type="hidden" name="re_num" id="re_num" value="'+ re_num +'">';
		modifyUI += '<textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content">' + re_content + '</textarea>';
		modifyUI += '<div id="mre_first"><span class="letter-count">300/300</span></div>';
		modifyUI += '<div id="mre_second" class="align-right">';
		modifyUI += ' <input type="submit" value="수정">';
		modifyUI += ' <input type="button" value="취소" class="re-reset">';
		modifyUI += '</div>';
		modifyUI += '<hr size="1" noshade width="96%">';
		modifyUI += '</form>';

		// 답글이 있는 경우 답글 초기화
		
		// 답글이 있는 경우 답글 초기화
		
		
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 sub-item
		//클래스로 지정된 div를 환원시키고 수정폼을 제거함
		initModifyForm();

		//수정버튼을 감싸고 있는 div
		$(this).parent().hide();

		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		$(this).parents('.item').append(modifyUI);

		//입력한 글자수 셋팅
		let inputLength = $('#mre_content').val().length;
		let remain = 300 - inputLength;
		remain += '/300';

		//문서 객체에 반영
		$('#mre_first .letter-count').text(remain);
	});
	//댓글 수정폼 초기화
	function initModifyForm() {
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	//수정폼에서 취소버튼 클릭시 수정폼 초기화
	$(document).on('click', '.re-reset', function() {
		initModifyForm();
	});
	
	//댓글 수정
	$(document).on('submit', '#mre_form', function(event) {
		if ($('#mre_content').val().trim() == '') {
			alert('내용을 입력하세요');
			$('#mre_content').val('').focus();
			return false;
		}

		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();
		console.log(form_data);

		//서버와 통신
		$.ajax({
			url: 'updateReply',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인해야 수정할 수 있습니다.');
				} else if (param.result == 'success') {
					$('#mre_form').parent().find('p').html($('#mre_content').val().replace(/</g, '&lt;')
																				  .replace(/>/g, '&gt;')
																				  .replace(/\r\n/g, '<br>')
																				  .replace(/\r/g, '<br>')
																				  .replace(/\n/g, '<br>'));
					$('#mre_form').parent().find('.modify-date').text('최근 수정일 : 5초미만');

					//수정폼 삭제 및 초기화
					initModifyForm();
				} else if (param.result == 'wrongAccess') {
					alert('타인의 글을 수정할 수 없습니다.');
				} else {
					alert('댓글 수정 오류 발생');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});



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
			} else if ($(this).attr('id') == 'mre_content') {
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			} else if ($(this).attr('id') == 'resp_content') {
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
	$(document).on('click','.delete-btn',function(){
		//댓글번호
		let re_num = $(this).attr('data-num');
		//서버와 통신
		$.ajax({
			url:'deleteReply',
			type:'post',
			data:{re_num:re_num},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(param.result == 'success'){
					alert('삭제 완료!');
					selectList(1);
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});	



	/*
	 *  댓글 수 표시
	 */
	function displayReplyCount(count) {
		let output;
		if (count > 0) {
			output = '댓글수(' + count + ')';
		} else {
			output = '댓글수(0)';
		}
		$('#output_rcount').text(output);
	}



	  /*------------------------
   *      댓글 좋아요 등록
   *------------------------*/
   $(document).on('click','.output_rfav',function(){
      let heart = $(this);
      //서버와 통신
      $.ajax({
         url:'writeReFav',
         type:'post',
         data:{re_num:heart.attr('data-num')},
         dataType:'json',
         success:function(param){
            if(param.result == 'logout'){
               alert('로그인 후 좋아요를 눌러주세요')
            }else if(param.result == 'success'){
               displayFav(param,heart);
            }else{
               alert('댓글 좋아요 등록/삭제 오류');
            }
         }
      });
   });
   /*------------------------
   *      댓글 좋아요 표시
   *------------------------*/
   function displayFav(param,heart){
      let output;
      if(param.status == 'noFav'){
         output = '../images/heart01.png';
      }else{
         output = '../images/heart02.png';
      }
      //문서 객체에 추가
      heart.attr('src',output);
      heart.parent().find('.output_rfcount').text(param.count);
   }
   





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