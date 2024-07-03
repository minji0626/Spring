package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
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

	// 글 좋아요
	public BoardFavVO selectFav(BoardFavVO fav);

	public Integer selectFavCount(Long board_num);

	public void insertFav(BoardFavVO fav);

	public void deleteFav(BoardFavVO fav);

	// 댓글
	public List<BoardReplyVO> selectListReply(Map<String, Object> map);

	public Integer selectRowCountReply(Map<String, Object> map);

	public BoardReplyVO selectReply(Long re_num);

	public void insertReply(BoardReplyVO boardReply);

	public void updateReply(BoardReplyVO boardReply);

	public void deleteReply(Long re_num);
}
