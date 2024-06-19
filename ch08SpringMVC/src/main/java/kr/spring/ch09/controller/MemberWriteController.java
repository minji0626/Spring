package kr.spring.ch09.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch09.vo.MemberVO;

@Controller
public class MemberWriteController {
	
	// 유효성 체크를 위한 자바빈(VO) 초기화
	@ModelAttribute("command")
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	// 폼 호출하기
	@GetMapping("/member/write.do")
	public String form() {
		return "member/write";
	}
	
	@PostMapping("/member/write.do")
	// valid 어노테이션을 작성하지 않으면 bindingresult의 값이 포함이 되지 않기 때문에 꼭 작성해야 한다
	public String submit(@ModelAttribute("command") @Valid MemberVO vo, BindingResult result) {
		
		System.out.println("전송된 데이터 : " + vo);
		
		if(result.hasErrors()) {
			return "member/write";
		}
		
		return "redirect:/index.jsp";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// true - 요청 파라미터의 값이 null이거나 빈 문자열("")일 때 
		// 변환 처리를 하지 않고 null 값으로 할당한다.
		// false - 변환 과정에서 에러가 발생하고 에러 코드로 "typeMismatch"가 추가된다.
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
	}
	
}
