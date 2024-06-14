package kr.spring.ch07;

public class RegisterService {
	// private 때문에 5번째 코드로 주소 할당 불가능함
	private RegisterDAO registerDAO;
	
										// 주소 들어옴
	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}

	public void write() {
		System.out.println("RegisterService의 write() 메서드 실행");
		
		registerDAO.insert();
	}
}
