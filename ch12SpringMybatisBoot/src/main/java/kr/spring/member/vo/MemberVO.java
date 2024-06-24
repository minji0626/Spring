package kr.spring.member.vo;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberVO {
	
	private int num;
	@NotBlank
	private String name;
	
	@NotBlank
	@Pattern(regexp="^[0-9a-zA-Z]+$")
	private String id;
	
	@NotBlank
	@Size(min = 4, max = 12)
	private String passwd;
	
	@NotBlank
	@Pattern(regexp = "^010\\d{8}$")
	private String phone;
	
	@NotBlank
	@Email
	private String email;
	
	private Date reg_date;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", name=" + name + ", id=" + id + ", passwd=" + passwd + ", phone="
				+ phone + ", email=" + email + ", reg_date=" + reg_date + "]";
	}
	
}
