package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	// sql session template를 사용하기 때문에 autowired를 통해 주입 받는다.
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
		
	@Override
	public void insertBoard(BoardVO board) {
		sqlSessionTemplate.insert("insertBoard", board);
	}

	@Override
	public int selectBoardCount() {
		//  MyBatis 프레임워크에서 사용되는 메서드로, SQL 쿼리를 실행하여 단일 결과 값을 반환할 때 사용
		// sql 문장을 읽어오는 식별자를 명시(ID) 
		// sql문장에 id를 부여하는데 해당 id를 제공
		// int를 반환해줌
		return sqlSessionTemplate.selectOne("selectBoardCount");
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Integer> map) {
		// map 안에 key와 value가 함께 저장이 되어있다.
											// ID				?에 전달할 Data 갖고 있음
		return sqlSessionTemplate.selectList("selectBoardList",map);
	}

	@Override
	public BoardVO selectBoard(int num) {
		return sqlSessionTemplate.selectOne("selectDetail", num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		sqlSessionTemplate.selectOne("updateBoard", board);
	}

	@Override
	public void deleteBoard(int num) {
		sqlSessionTemplate.selectOne("deleteBoard", num);
	}
}
