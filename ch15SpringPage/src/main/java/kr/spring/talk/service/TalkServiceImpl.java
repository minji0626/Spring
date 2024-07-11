package kr.spring.talk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.talk.dao.TalkMapper;
import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

@Service
@Transactional
public class TalkServiceImpl implements TalkService{
	
	@Autowired
	TalkMapper talkMapper;

	@Override
	public List<TalkRoomVO> selectTalkRoomList(Map<String, Object> map) {
		return null;
	}

	@Override
	public Integer selectRowCount(Map<String, Object> map) {
		return null;
	}

	@Override
	public void insertTalkRoom(TalkRoomVO talkRoomVO) {
		
	}

	@Override
	public List<TalkMemberVO> selectTalkmember(Long talkroom_num) {
		return null;
	}

	@Override
	public void insertTalk(TalkVO talkVO) {
		
	}

	@Override
	public List<TalkVO> selectTalkDetail(Map<String, Long> map) {
		return null;
	}

	@Override
	public void deleteTalkRead(Map<String, Long> map) {
		
	}

}
