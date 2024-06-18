package kr.spring.ch08.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch08.vo.SubmitReportVO;

public class SubmitReportValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SubmitReportVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SubmitReportVO vo = (SubmitReportVO)target;
		if(vo.getSubject() == null || vo.getSubject().trim().isEmpty()) {
			errors.rejectValue("subject", "required");
		}
		if(vo.getReportFile() == null || vo.getReportFile().isEmpty()) {
			errors.rejectValue("reportFile", "required");
		}
		
	}

}
