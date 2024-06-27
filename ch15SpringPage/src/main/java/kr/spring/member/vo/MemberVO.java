package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"photo"})
public class MemberVO {
	private long mem_num;
	
	@Pattern(regexp = "^[0-9a-zA-Z]{4,12}$")
	private String id;
	
	private String nick_name;
	private int auth;
	private String auto;	// 컬럼은 없지만 필요에 의해 생성해줌
	private String au_id;
	
	@NotBlank
	private String name;
	@Pattern(regexp = "^[0-9a-zA-Z]{4,12}$")
	private String passwd;
	@NotBlank
	private String phone;
	@NotBlank
	private String email;
	@Size(min = 5,max = 5)
	private String zipcode;
	@NotBlank
	private String address1;
	@NotBlank
	private String address2;
	
	private byte[] photo;
	private String photo_name;
	private Date reg_date;
	private Date modify_date;
	
	// 비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
	@Pattern(regexp = "^[0-9a-zA-Z]{4,12}$")
	private String now_passwd;	// 컬럼은 없지만 필요에 의해 생성해줌
	
	public boolean ischeckedPassword(String userPasswd) {
		if(auth > 1 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	// 이미지 BLOB 처리
	// (주의) 폼에서 파일 업로드 파라미터 네임은 반드시 upload로 지정해야 한다
	public void setUpload(MultipartFile upload) throws IOException {
		// MultipartFile -> byte []
		setPhoto(upload.getBytes());
		
		//파일 이름
		setPhoto_name(upload.getOriginalFilename());
	}
	
}
