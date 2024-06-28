package kr.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	public BoardService boardService;
	
	// 자바빈 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	/* ==============
	 * 게시판 글 작성
	 * ==============
	 * */
	// 게시판 등록 폼 호출
	@GetMapping("/board/write")
	public String form() {
		return "boardWrite";
	}
	
	
	/* =============
	 * 게시판 목록
	 * =============
	 * */
	@GetMapping("/board/list")
	public String getList(@RequestParam(defaultValue = "1") int pageNum, 
							@RequestParam(defaultValue = "1") int order, @RequestParam(defaultValue = "") String category,
							String keyfield, String keyword, Model model) {
		log.debug("<< 게시판 목록 - category >> : " + category);
		log.debug("<< 게시판 목록 - order >> : " + order);
		
		return "boardList";
	}
}
