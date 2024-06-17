package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	// 요청 URL과 실행 메서드 연결
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		// model = 데이터(request) view = 뷰(viewResolver)
		ModelAndView mav = new ModelAndView();
		
		// 뷰 이름 지정 -> hello가 어딨는지 찾고, 확장자도 알려주기 때문에 이름만 명시
		mav.setViewName("hello"); 	// /WEB-INF/views/ -> prefix hello. jsp->s
		
		// 뷰에서 사용할 데이터 세팅
		// request에 저장되기 때문에 el을 이용하여 데이터 출력할 수 있음
		mav.addObject("greeting","안녕하세요!");
		mav.addObject("lunch","곧 점심시간입니다!");
		
		return mav;
	}
}
