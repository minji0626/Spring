package example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import example.vo.NewAccountVO;

public class NewAccount {
	
	// 파일 업로드 경로 설정
	@Value("${file_path}")
	private String path;
	
	// 유효성 체크를 위한 자바빈을 설정한다
	@ModelAttribute("newaccount")
	public NewAccountVO initCommand() {
		return new NewAccountVO();
	}
	
	// 회원가입 폼을 호출한다
	@GetMapping("/account/newAccount.do")
	public String form() {
		return "account/newAccountForm";
	}

}
