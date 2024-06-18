package kr.spring.ch07.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch07.vo.LoginVO;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginVO vo = (LoginVO)target;
		if(vo.getUserId() == null || vo.getUserId().trim().isEmpty()) {
			errors.rejectValue("userId", "required");
		}
		if(vo.getPassword() == null || vo.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required");
		}
	}
}
