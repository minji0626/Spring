package kr.spring.member.service;

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
		// 여기서 insert 문들을 모두 조합해서 정보를 전달
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
	}

	@Override
	public MemberVO selectCheckMember(String id) {
		return null;
	}

	@Override
	public MemberVO selectMember(Long mem_num) {
		return null;
	}

	@Override
	public void updateMember(MemberVO member) {
		
	}

	@Override
	public void updatePassword(MemberVO member) {
		
	}

	@Override
	public void deleteMember(Long mem_num) {
		
	}

}
