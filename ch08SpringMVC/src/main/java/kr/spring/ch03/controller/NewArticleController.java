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
		[형식]
		1. @ModelAttribute(속성명) NewArticleVO vo
			지정한 속성명으로 JSP에서 request에 접근하여 자바빈(VO) 호출 가능
			ex) ${속성명.title} , ${속성명.name}, ${속성명.content}
		2. @ModelAttribute를 명시할 때 속성명을 생략할 수 있다.
			속성명을 생략하면 클래스명의 첫 글자를 소문자로 속성명을 자동 생성한다.
			ex) @ModelAttribute NewArticleVO vo
				${newArticleVO.title} , ${newArticleVO.name},${newArticleVO.content}
		3. @ModelAttribute 생략
			호출 메서드에 인자명만 명시
			ex) NewArticleVO vo와 같이 인자명만 명시
			request에 저장되는 속성명은 newArticleVO로 저장됨
	 */
	
	// post 요청이 들어올 때 호출
	@PostMapping("/article/newArticle.do")
	public String submit(NewArticleVO vo) {
		
		newArticleService.writeArticle(vo);
				// 뷰 이름 지정
		return "article/newArticleSumitted";
	}
	
	
}
