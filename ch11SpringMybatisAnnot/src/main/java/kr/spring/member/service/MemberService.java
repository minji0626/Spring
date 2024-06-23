package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	public void insertMember(MemberVO member);
	public int selectMemberCount();
	public List<MemberVO> selectMemberList(Map<String , Integer> map);
	public MemberVO selectMember(int num);
	public void updateMember(MemberVO member);
	public void deleteMember(int num);
}
