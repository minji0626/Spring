package kr.spring.ch03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.ch03.service.NewArticleService;
import kr.spring.ch03.vo.NewArticleVO;

@Controller
public class NewArticleController {
	
	// autowired가 set을 자동으로 만들어주기 때문에 굳이 만들 필요가 없다
	@Autowired
	private NewArticleService newArticleService;

	// get 요청이 들어올 때 호출
	@GetMapping("/article/newArticle.do")
	public String form() {
		
				// 뷰 이름 지정
		return "article/newArticleForm";
	}
	
	/*
	 	@ModelAttribute 어노테이션을 이용해서 전송된 데이터를 자바빈에 담기
		[기능]
		1. 자바빈(VO) 생성
		2. 전송된 데이터를 자바빈에 저장
		3. View에서 사용할 수 있도록 request에 자바빈(VO)를 저장
	 */
	
	// post 요청이 들어올 때 호출
	@PostMapping("/article/newArticle.do")
	public String submit(@ModelAttribute("command") NewArticleVO vo) {
		
		newArticleService.writeArticle(vo);
				// 뷰 이름 지정
		return "article/newArticleSumitted";
	}
	
	
}
