package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	@Insert("INSERT INTO amember VALUES(amember_seq.nextval,#{name},#{id},#{passwd},#{phone},#{email},SYSDATE)")
	public void insertMember(MemberVO member);
	
	@Select("SELECT COUNT(*) FROM amember")
	public int selectMemberCount();
	
	public List<MemberVO> selectMemberList(Map<String , Integer> map);
	
	@Select("SELECT * FROM amember WHERE num=#{num}")
	public MemberVO selectMember(int num);
	
	@Update("UPDATE amember SET name=#{name}, id=#{id}, phone=#{phone}, email=#{email} WHERE num=${num}")
	public void updateMember(MemberVO member);
	
	@Delete("DELETE FROM amember WHERE num=#{num}")
	public void deleteMember(int num);
	
}
