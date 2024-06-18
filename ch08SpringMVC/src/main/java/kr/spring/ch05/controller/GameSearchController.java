package kr.spring.ch05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch05.service.SearchService;
import kr.spring.ch05.vo.SearchVO;

@Controller
public class GameSearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search/main.do")
	public String main() {
				// 뷰 이름 지정
		return"search/main";
	}
	
	@RequestMapping("/search/game.do")
	public ModelAndView search(@ModelAttribute("vo") SearchVO searchVO) {
		System.out.println("검색어 = " + searchVO.getQuery());
		
		String result = searchService.search(searchVO);
		
		ModelAndView mav = new ModelAndView();
		// 뷰 이름 지정하기
		mav.setViewName("search/game");
		// 뷰에서 사용할 데이터 저장
		mav.addObject("searchResult",result);
		return mav;
	}
}
