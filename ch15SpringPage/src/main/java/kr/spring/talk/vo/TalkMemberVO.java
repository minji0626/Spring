package kr.spring.talk.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TalkMemberVO {
	private long talkroom_num;			// 채팅방 멤버 관리 번호
	private long mem_num;				// 멤버 회원번호
	private String room_name;			// 채팅방 이름
		
	private String id;			// 아이디
}
