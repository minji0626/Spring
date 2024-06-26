package kr.spring.member.dao;

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
	
	public void updateMember(MemberVO member);
	
	@Update("UPDATE spmember_detail set name=#{name}, phone=#{phone}, email=#{email}, zipcode=#{zipcode}, address1=#{address1}, address2=#{address2} modify_date=SYSDATE WHERE mem_num=#{mem_num}")
	public void updateMember_detail(MemberVO member);
	
	@Update("UPDATE spmember_detail set passwd=#{passwd} WHERER mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	@Delete("DELETE FROM spmember WHERE mem_num = #{mem_num}")
	public void deleteMember(Long mem_num);
	
	@Delete("DELETE FROM spmember_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Long mem_num);
}
