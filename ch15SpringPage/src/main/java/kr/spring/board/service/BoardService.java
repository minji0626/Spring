package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

public interface BoardService {
	// 글
	public List<BoardVO> selectList(Map<String, Object> map);

	public Integer selectRowCount(Map<String, Object> map);

	public void insertBoard(BoardVO board);

	public BoardVO selectBoard(Long board_num);

	public void updateHit(Long board_num);

	public void updateBoard(BoardVO board);

	public void deleteBoard(Long board_num);

	public void deleteFile(Long board_num);
}
