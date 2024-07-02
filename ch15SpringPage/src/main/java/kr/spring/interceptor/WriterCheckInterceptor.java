package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriterCheckInterceptor implements HandlerInterceptor{

	@Autowired
	private BoardService boardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		log.debug("<< 로그인 회원 번호와 작성자 회원 번호 일치 여부 체크 >>");
		
		// 로그인 회원 번호 구하기
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		// 작성자 회원 번호 구하기 - input hidden 이걸로 보낸 board_num을 이용한다
		long board_num = Long.parseLong(request.getParameter("board_num"));
		
		BoardVO board = boardService.selectBoard(board_num);
		
		if(user != null) {
			log.debug("<< 로그인 회원 번호 >>" + user.getMem_num());
			log.debug("<< 작성자 회원 번호 >>" + board.getMem_num());
		}
		
		// 로그인 회원번호와 작성자 회원 번호 일치 여부 체크
		if(user == null || user.getMem_num() != board.getMem_num()) {
			log.debug("<< 로그인 회원 번호와 작성자 회원 번호 불일치 >>");
			 request.setAttribute("accessMsg", "로그인 아이디와 작성자 아이디 불일치 ");
			 request.setAttribute("accessBtn", "게시판 목록");
			 request.setAttribute("accessUrl", request.getContextPath()+"/board/list");
			 
			 // forward 방식으로 화면 호출하기
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			 dispatcher.forward(request, response);
			 
			 return false;
		}
		log.debug("<< 로그인 회원번호와 작성자 회원번호 일치 >>");
		return true;
	}
}
