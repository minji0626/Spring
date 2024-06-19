package kr.spring.ch09.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;


public class MemberVO {
	
	// 정규표현식으로 패턴 검사
	//  0~9, a~z, A~Z, + : 최소 한 번 이상 반복하라는 의미
	@Pattern(regexp="^[0-9a-zA-Z]+$")
	private String id;
	
	// 문자열의 길이를 지정한다
	// 최소 4자리, 최대 10자리까지
	@Size(min = 4, max = 10)
	private String password;
	
	// 필수 입력하도록 지정하기
	@NotEmpty
	private String name;
	
	// 이메일은 필수 입력, 이메일 형식에 맞게 작성하기
	@Email
	@NotEmpty
	private String email;
	
	// pom.xml에 라이브러리 추가한 이유는 range 어노테이션을 사용하기 위해서
	// 숫자 데이터 체크하기 - 최소 1, 최대 200
	@Range(min = 1 , max = 200)
	private Integer age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", age=" + age
				+ "]";
	}
	
}
