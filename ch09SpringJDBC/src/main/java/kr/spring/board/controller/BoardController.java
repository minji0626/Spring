package kr.spring.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/*
	 로그 레벨
	 FATAL : 가장 심각한 오류를 의미
	 ERROR : 일반적인 오류
	 WARN : 주의를 요하는 경우
	 INFO : 런타임시 관심있는 경우
	 DEBUG : 시스템 흐름과 관련된 상세 정보
	 TRACE : 가장 상세한 정보
	 */
	
	// 로그 처리(로그 대상 지정)
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	
	// 유효성 체크를 위한 폼 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	@RequestMapping("/list.do")
	// value = pageNum, 따로 들어오는 값이 없다면 1유지
	public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {
		
		int count = boardService.getBoardCount();
		
		log.debug("<<count>> : "+ count );
		
		// 페이지 처리하기
		PagingUtil page = new PagingUtil(currentPage, count, 20,10, "list.do");
		
		// 목록 호출
		List<BoardVO> list = null;
		
		if(count > 0) {
			list = boardService.getBoardList(page.getStartRow(), page.getEndRow());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	
	// 폼 호출하기
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	
	// 폼 입력하기
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO vo, BindingResult result) {
		
		log.debug("<<BoardVO>> : " + vo);
		
		// 유효성 체크 결과 오류가 있다면 폼을 호출한다
		if(result.hasErrors()) {
			return form();
		}
		
		// 글 등록하기
		boardService.insertBoard(vo);
		
		return "redirect:/list.do";
	}
	
	// 글 상세보기
	@RequestMapping("/detail.do")
	public ModelAndView detail(int num) {
		log.debug("<<num>> : " + num);
		
		BoardVO board = boardService.getBoard(num);
							//  뷰 이름			속성명	속성값
		return new ModelAndView("selectDetail", "board",board);
	}

	// 수정하는 폼
	@GetMapping("/update.do")
	public String formUpdate(int num, Model model) {
		model.addAttribute("boardVO", boardService.getBoard(num));
		return "updateForm";
	}
	
	// 수정하기(전송된 데이터 처리)
	@PostMapping("/update.do")
	public String submitUpdate(@Valid BoardVO vo, BindingResult result) {
		
		log.debug("<<UpdateBoardVO>> : "+ vo);
		
		if(result.hasErrors()) {
			return "updateForm";
		}
		
		// DB에서 저장된 
		BoardVO db_board = boardService.getBoard(vo.getNum());
		
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "updateForm";
			}
		
		boardService.updateBoard(vo);
		
		return "redirect:/list.do";
	}
	
	// 삭제 폼
	@GetMapping("/delete.do")
	public String formDelete(BoardVO vo) {
		return "deleteForm";
	}
	
	// 전송된 데이터 처리하기
	@PostMapping("/delete.do")
	public String submitDelete(@Valid BoardVO vo, BindingResult result) {
		
		log.debug("<<deleteBoard>> : " + vo);
		
		// 비밀번호만 유효성 체크 결과 오류가 있다면 폼 호출
		if(result.hasFieldErrors("passwd")) {
			return "deleteForm";
		}
		
		// DB에서 저장된 
		BoardVO db_board = boardService.getBoard(vo.getNum());
				
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		
		boardService.deleteBoard(vo.getNum());
		
		return "redirect:/list.do";
	}
	
	
}
