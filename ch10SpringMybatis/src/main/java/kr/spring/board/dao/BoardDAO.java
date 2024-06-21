package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

// 구조를 넣기 위해 생성
public interface BoardDAO {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	
	// map으로 묶어서 보내기 object는 검색의 경우(지금은 검색이 없기 때문에 Integer를 보낸다)
	// mybatis는 하나의 인자만 사용한다. 무조건적으로 map으로 묶어서 보내야한다. (규칙)
	// 인자 1개(map), 반환 타입 1개
	// Sql Session Template를 사용하기 때문에 규칙을 따라야 한다.
	public List<BoardVO> selectBoardList(Map<String, Integer> map);
	
	public BoardVO selectBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);
}
