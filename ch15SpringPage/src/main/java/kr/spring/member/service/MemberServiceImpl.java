package kr.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public void insertMember(MemberVO member) {
		// 여기서 insert 문들을 모두 조합해서 정보를 전달
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
	}

	@Override
	public MemberVO selectCheckMember(String id) {
		return memberMapper.selectCheckMember(id);
	}

	@Override
	public MemberVO selectMember(Long mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
		memberMapper.updateMember_detail(member);
	}

	@Override
	public void updatePassword(MemberVO member) {
		memberMapper.updatePassword(member);
	}

	@Override
	public void deleteMember(Long mem_num) {
		
	}

	@Override
	public void updateAu_id(String au_id, Long mem_num) {
		
	}

	@Override
	public void selectAu_id(String au_id) {
		
	}

	@Override
	public void deleteAu_id(Long mem_num) {
		
	}

	@Override
	public void updateRandomPassword(MemberVO member) {
		
	}

	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
	}

	@Override
	public List<MemberVO> selectSearchMember(String id) {
		return memberMapper.selectSearchMember(id);
	}

}
