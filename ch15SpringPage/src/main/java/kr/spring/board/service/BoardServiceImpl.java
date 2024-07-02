package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public Integer selectRowCount(Map<String, Object> map) {
		return boardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(BoardVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardVO selectBoard(Long board_num) {
		return boardMapper.selectBoard(board_num);
	}

	@Override
	public void updateHit(Long board_num) {
		boardMapper.updateHit(board_num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Long board_num) {
		
		// 답글 삭제
		// 댓글 좋아요 삭제
		// 댓글 삭제
		// 부모글 좋아요 삭제
		boardMapper.deleteFavByBoardNum(board_num);
		// 부모글 삭제
		boardMapper.deleteBoard(board_num);
	}

	@Override
	public void deleteFile(Long board_num) {
		boardMapper.deleteFile(board_num);
	}

	@Override
	public BoardFavVO selectFav(BoardFavVO fav) {
		return boardMapper.selectFav(fav);
	}

	@Override
	public Integer selectFavCount(Long board_num) {
		return boardMapper.selectFavCount(board_num);
	}

	@Override
	public void insertFav(BoardFavVO fav) {
		boardMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(BoardFavVO fav) {
		boardMapper.deleteFav(fav);
	}
	
}
