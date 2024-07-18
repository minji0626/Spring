package kr.spring.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	// 회원 관리 - 일반 회원
	@Select("SELECT spmember_seq.nextval FROM dual")
	public Long selectMem_num();
	
	@Insert("INSERT INTO spmember (mem_num, id, nick_name, auth) VALUES (#{mem_num}, #{id}, #{nick_name}, 2)")
	public void insertMember(MemberVO member);
	
	// xml
	public void insertMember_detail(MemberVO member);
	
	// xml
	public MemberVO selectCheckMember(String id);
	
	@Select("SELECT * FROM spmember JOIN spmember_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Long mem_num);
	
	@Update("UPDATE spmember SET nick_name=#{nick_name} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	public void updateMember_detail(MemberVO member);
	
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	@Delete("DELETE FROM spmember WHERE mem_num = #{mem_num}")
	public void deleteMember(Long mem_num); 
	
	@Delete("DELETE FROM spmember_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Long mem_num);
	
	// 자동 로그인 처리
	@Update("UPDATE spmember_detail SET au_id=#{au_id} WHERE mem_num=#{mem_num}")
	public void updateAu_id(String au_id, Long mem_num);
	@Select("SELECT m.mem_num,m.id,m.auth,d.au_id,d.passwd,m.nick_name,d.email FROM spmember m JOIN spmember_detail d ON m.mem_num = d.mem_num WHERE d.au_id=#{au_id}")
	public MemberVO selectAu_id(String au_id);
	@Update("UPDATE spmember_detail set au_id='' WHERE mem_num=#{mem_num}")
	public void deleteAu_id(Long mem_num);
	
	
	// 비밀번호 찾기
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updateRandomPassword(MemberVO member);
	
	// 플필 이미지 업데이트
	@Update("UPDATE spmember_detail SET photo=#{photo}, photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	// 채팅 회원 정보 검색
	@Select("SELECT mem_num, id, nick_name FROM spmember WHERE auth >=2 AND id LIKE '%' || #{id} || '%' ")
	public List<MemberVO> selectSearchMember(String id);
	
	
}
