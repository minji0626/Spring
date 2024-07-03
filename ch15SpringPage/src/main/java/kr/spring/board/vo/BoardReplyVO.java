package kr.spring.board.vo;

import java.sql.Date;

import kr.spring.util.DurationFromNow;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BoardReplyVO {
	private Long re_num;
	private String re_content;
	private String re_date;
	private String re_mdate;
	private String re_ip;
	private Long board_num;
	private Long mem_num;
	
	private String id;
	private String nick_name;
	
	// 로그인한 상태에서 클릭한 사람의 정보를 읽기, 로그인 하지 않으면 0을 전달
	// 댓글 좋아요 작업
	private int click_num;
	private int refave_cnt;	// 댓글 좋아요 개수
	
	
	// 답글
	private int resp_cnt;	// 답글 개수
	
	public void setRe_date(String re_date) {
		this.re_date = DurationFromNow.getTimeDiffLabel(re_date);
	}
	
	public void setRe_mdate(String re_mdate) {
		this.re_mdate = DurationFromNow.getTimeDiffLabel(re_mdate);
	}

}
