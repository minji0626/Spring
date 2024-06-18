package kr.spring.ch07.service;

import kr.spring.ch07.vo.LoginVO;

public class LoginService {
	public void checkLogin(LoginVO vo) throws LoginCheckException{
		// 테스트용으로 userId와 password가 일치하면 로그인 처리
		if(!vo.getUserId().equals(vo.getPassword())) {
			System.out.println("인증 에러 - " + vo.getUserId());
			throw new LoginCheckException();
		}
	}
}
