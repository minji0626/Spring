package kr.spring.talk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

@Mapper
public interface TalkMapper {
	// 채팅방 목록
	public List<TalkRoomVO> selectTalkRoomList(Map<String, Object> map);
	public Integer selectRowCount(Map<String,Object> map);
	
	// 채팅방 번호 생성하기
	public Integer selectTalkRoomNum();
	// 채팅방 생성하기
	public void insertTalkRoom(TalkRoomVO talkRoomVO);
	
	// 채팅방 멤버 읽기 
	public List<TalkMemberVO> selectTalkmember(Long talkroom_num);
	
	// 채팅 메세지 번호 생성하기
	public Integer selectTalkNum();
	// 채팅 메세지 등록하기
	public void insertTalk(TalkVO talkVO);
	// 채팅 메세지 읽기
	public List<TalkVO> selectTalkDetail(Map<String, Long> map);
	// 읽은 채팅 기록 삭제
	public void deleteTalkRead(Map<String, Long> map);
}
