package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

// mybatis가 자동으로 implement 해서 생성시켜준다
@Mapper
public interface BoardMapper {
	
		@Insert("INSERT INTO aboard VALUES(aboard_seq.nextval,#{writer},#{title},#{passwd},#{content},SYSDATE)")
		public void insertBoard(BoardVO board);
		
		@Select("SELECT COUNT(*) FROM aboard")
		public int getBoardCount();
		
		public List<BoardVO> getBoardList(Map<String, Integer> map);
		
		@Select("SELECT * FROM aboard WHERE num=#{num}")
		public BoardVO selectBoard(int num);
		
		@Update("UPDATE aboard SET writer=#{writer}, title=#{title}, content=#{content} WHERE num=#{num}")
		public void updateBoard(BoardVO board);
		
		@Delete("DELETE FROM aboard WHERE num=#{num}")
		public void deleteBoard(int num);
		
		
}