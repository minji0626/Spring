package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	// 글
	public List<BoardVO> selectList(Map<String, Object> map);
	public Integer selectRowCount(Map<String, Object> map);	// integer, int 상관 없음
	public void insertBoard(BoardVO board);
	public BoardVO selectBoard(Long board_num);
	public void updateHit(Long board_num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(Long board_num);
	public void deleteFile(Long board_num);
	// 글 좋아요
	
	// 댓글
	
	// 댓글 좋아요
	
	// 대댓글
}
