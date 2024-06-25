package kr.spring.member.service;
import kr.spring.member.vo.MemberVO;

public interface MemberService {
	// 회원 관리 - 일반 회원
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String id);
	public MemberVO selectMember(Long mem_num);
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(Long mem_num);
}
