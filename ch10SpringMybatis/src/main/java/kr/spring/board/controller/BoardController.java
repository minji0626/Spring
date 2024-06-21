package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 유효성 체크를 위한 폼 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}

	// 로그 처리하기
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping("/list.do")
	// pageutil 에서 사용하는 값
	public ModelAndView getList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {
		// 총 레코드 수
		int count = boardService.selectBoardCount();
		log.debug("<<count>> : " + count);

		// 페이지 처리하기
		PagingUtil page = new PagingUtil(currentPage, count, 20, 10, "list.do");

		// 목록 호출하기
		List<BoardVO> list = null;
		if (count > 0) {
			// map에 담겨서 넘기기 때문에 map 생성
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = boardService.selectBoardList(map);
		}

		ModelAndView mav = new ModelAndView();
		// 뷰 이름 설정
		mav.setViewName("selectList");
		// 데이터 저장
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}

	// 폼 호출하기
	@GetMapping("/insert.do")
	public String insertForm() {
		return "insertForm";
	}

	// 폼 등록하기
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO vo, BindingResult result) {
		// 로그 호출
		log.debug("<<BoardVo>> : " + vo);
		// 유효성 체크
		if (result.hasErrors()) {
			return insertForm();
		}
		// 글 삽입
		boardService.insertBoard(vo);

		return "redirect:/list.do";
	}

	// 글 상세보기
	@RequestMapping("/detail.do")
	public ModelAndView detail(int num) {
		log.debug("<<num>> : " + num);

		BoardVO board = boardService.selectBoard(num);
		// 뷰 이름 속성명 속성값
		return new ModelAndView("selectDetail", "board", board);
	}

	// 글 수정 폼 호출하기
	@GetMapping("/update.do")
	public String updateForm(int num, Model model) {
		model.addAttribute("boardVO", boardService.selectBoard(num));
		return "updateForm";
	}
	
	// 글 수정하기
	@PostMapping("/update.do")
	public String submitUpdate(@Valid BoardVO vo, BindingResult result) {
		log.debug("<<UpdateBoardVO>> : "+ vo);
		if(result.hasErrors()) {
			return "updateForm";
		}
		// DB에서 저장된 
		BoardVO db_board = boardService.selectBoard(vo.getNum());
		
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
				result.rejectValue("passwd", "invalidPassword");
				return "updateForm";
			}
		
		boardService.updateBoard(vo);
		
		return "redirect:/list.do";
	}
	
	// 글 삭제하기 폼 호출하기
	@GetMapping("/delete.do")
	public String deleteForm(BoardVO vo) {
		return "deleteForm";
	}
	
	// 글 삭제하기
	@PostMapping("/delete.do")
	public String submitDelete(@Valid BoardVO vo, BindingResult result) {

		log.debug("<<Delete Board>> : " + vo);

		if (result.hasFieldErrors("passwd")) {
			return "deleteForm";
		}

		// DB에서 저장된
		BoardVO db_board = boardService.selectBoard(vo.getNum());

		if (!db_board.getPasswd().equals(vo.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		
		boardService.deleteBoard(vo.getNum());
			
		return "redirect:/list.do";
	}
}
