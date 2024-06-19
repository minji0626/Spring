package kr.spring.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.vo.BoardVO;

@Controller
public class BoardController {
	
	// 유효성 체크를 위한 폼 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	@RequestMapping("/list.do")
	public ModelAndView process() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectList");
		
		return mav;
	}
	
	// 폼 호출하기
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	
}
