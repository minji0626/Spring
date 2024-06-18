package kr.spring.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch06.validator.MemberValidator;
import kr.spring.ch06.vo.MemberVO;

@Controller
public class CreateAccountController {
	
	// 유효성 체크할 경우 form:form태그에 자바빈을 지정해야 하기 때문에
	// 폼이 호출 되기 전에 자바빈을 생성해서 전달
	// 자바빈 초기화
	@ModelAttribute("command")
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	// 폼 호출하기
	@GetMapping("/account/create.do")
	public String form() {
		return "account/creationForm";
	}
	
	// 폼 등록하기 
	@PostMapping("/account/create.do")
	public String submit(@ModelAttribute("command")MemberVO vo, BindingResult result) {
		
		System.out.println(vo);
		
		// 전송된 데이터 유효성 체크
								// 자바빈 , binding result 저장
		new MemberValidator().validate(vo, result);
		
		// BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장되어 있으면
		// 폼을 다시 호출한다
		if(result.hasErrors()) {
			return "account/creationForm";
		}
		
		return "account/created";
	}
	
}
