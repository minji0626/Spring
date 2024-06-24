package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void insertMember(MemberVO member) {
		memberMapper.insertMember(member);
	}

	@Override
	public int selectMemberCount() {
		return memberMapper.selectMemberCount();
	}

	@Override
	public List<MemberVO> selectMemberList(Map<String, Integer> map) {
		return memberMapper.selectMemberList(map);
	}

	@Override
	public MemberVO selectMember(int num) {
		return memberMapper.selectMember(num);
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
	}

	@Override
	public void deleteMember(int num) {
		memberMapper.deleteMember(num);
	}
	
}
