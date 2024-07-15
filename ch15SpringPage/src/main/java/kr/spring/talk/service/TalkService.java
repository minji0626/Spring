package kr.spring.talk.service;

import java.util.List;
import java.util.Map;

import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

public interface TalkService {
	public List<TalkRoomVO> selectTalkRoomList(Map<String, Object> map);
	public Integer selectRowCount(Map<String,Object> map);
	public void insertTalkRoom(TalkRoomVO talkRoomVO);
	public List<TalkMemberVO> selectTalkMember(Long talkroom_num);
	public void insertTalk(TalkVO talkVO);
	public List<TalkVO> selectTalkDetail(Map<String, Long> map);
	public void deleteTalkRead(Map<String, Long> map);
}
