package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		return null;
	}

	@Override
	public Integer selectRowCount(Map<String, Object> map) {
		return null;
	}

	@Override
	public void insertBoard(BoardVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardVO selectBoard(Long board_num) {
		return null;
	}

	@Override
	public void updateHit(Long board_num) {
		
	}

	@Override
	public void updateBoard(BoardVO board) {
		
	}

	@Override
	public void deleteBoard(Long board_num) {
		
	}

	@Override
	public void deleteFile(Long board_num) {
		
	}
	
	
}
