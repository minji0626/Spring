package kr.spring.ch06.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch06.vo.MemberVO;

public class MemberValidator implements Validator{

	// Valdator가 검증할 수 있는 타입인지를 검사
	@Override
	public boolean supports(Class<?> clazz) {
		// 유효성 검사를 진행하는 객체가 java bean의 형태인지 확인
		return MemberVO.class.isAssignableFrom(clazz);
	}
	
	// 유효성 체크를 실질적으로 수행하는 메서드
	// target : 검증하는 자바빈(VO) 객체
	// errors : 예외의 정보를 담는 객체
	@Override
	public void validate(Object target, Errors errors) {
		MemberVO vo = (MemberVO)target;
		if(vo.getId() == null || vo.getId().trim().isEmpty()) {
							// 필드	에러코드	에러 문구	
			errors.rejectValue("id", null, "ID는 필수 항목입니다.");
		}
		if(vo.getName() == null || vo.getName().trim().isEmpty()) {
			errors.rejectValue("name", null, "이름은 필수 항목입니다.");
		}
		if(vo.getZipcode() == null || vo.getZipcode().trim().isEmpty()) {
			errors.rejectValue("zipcode", null, "우편번호는 필수 항목입니다.");
		}
		if(vo.getAddress1() == null || vo.getAddress1().trim().isEmpty()) {
			errors.rejectValue("address1", null, "주소는 필수 항목입니다.");
		}
		if(vo.getAddress2() == null || vo.getAddress2().trim().isEmpty()) {
			errors.rejectValue("address2", null, "상세 주소는 필수 항목입니다.");
		}
	}
	
}
