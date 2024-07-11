package kr.spring.talk.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TalkVO {
	private long talk_num;		//채팅 번호 
	private long talkroom_num;		// 채팅방 번호 (수신 그룹)
	private long mem_num;		// 발신자
	private String message;		// 메세지
	private String chat_date;	// 작성일
	
	private int read_count;	// 읽지 않은 메세지 수
	private String id;		// 작성자 아이디
	
}
