package kr.spring.board.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import kr.spring.board.vo.BoardVO;

@Controller
public class BoardController {
	
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
	
	// 폼 입력하기
	@PostMapping("/insert.do")
	public String submit(@ModelAttribute @Valid BoardVO vo, BindingResult result) {
		
		log.debug("<<BoardVO>> : " + vo);
		
		// 유효성 체크 결과 오류가 있다면 폼을 호출한다
		if(result.hasErrors()) {
			return form();
		}
		
		return "redirect:/list.do";
	}
	
}
