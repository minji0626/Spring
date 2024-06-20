package kr.spring.board.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardDAO;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void insertBoard(BoardVO board) {
		boardDAO.insertBoard(board); 
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) {
		return boardDAO.getBoardList(startRow, endRow);
	}

	@Override
	public BoardVO getBoard(int num) {
		return boardDAO.getBoard(num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardDAO.updateBoard(board);
	}

	@Override
	public void deleteBoard(int num) {
		boardDAO.deleteBoard(num);
	}

}
