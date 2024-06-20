package kr.spring.board.service;

import java.util.List;

import kr.spring.board.vo.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO board);
	public int getBoardCount();
	public List<BoardVO> getBoardList(int startRow, int endRow);
	public BoardVO getBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);
}
