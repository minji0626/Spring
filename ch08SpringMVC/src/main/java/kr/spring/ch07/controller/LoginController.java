package kr.spring.ch07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch07.service.LoginCheckException;
import kr.spring.ch07.service.LoginService;
import kr.spring.ch07.validator.LoginValidator;
import kr.spring.ch07.vo.LoginVO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	// 유효성 체크를 위한 자바빈 초기화
	@ModelAttribute
	public LoginVO initCommand() {
		return new LoginVO();
	}
	
	// 폼 호출하기
	@GetMapping("/login/login.do")
	public String form() {
		return "login/form";
	}
	
	
	@PostMapping("/login/login.do")
	public String submit(@ModelAttribute LoginVO loginVO, BindingResult result) {
		
		System.out.println(loginVO);
		
		new LoginValidator().validate(loginVO, result);
		if(result.hasErrors()) {
			return "login/form";
		}
		
		// 로그인 체크
		try {
			loginService.checkLogin(loginVO);
			// 로그인 성공
			return "redirect:/index.jsp";
		} catch (LoginCheckException e) {
			// 로그인 실패
			// 메세지 처리하기 	에러코드
			result.reject("invalidIdOrPassword");
			return form();
		}  
		
	}

}
