package kr.spring.board.vo;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/*
@NotNull : null만 허용하지 않음
@NotBlank: null, ""(빈문자열)," "(공백)을 허용하지 않음
@NotEmpty : null과 ""(빈문자열)을 허용하지 않음
 * */

public class BoardVO {
	
	private int num;
	
	@NotBlank
	private String writer;
	
	@NotEmpty
	private String title;
	
	@NotBlank
	private String passwd;
	
	@NotEmpty
	private String content;
	
	private Date reg_date;
	

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", writer=" + writer + ", title=" + title + ", passwd=" + passwd + ", content="
				+ content + ", reg_date=" + reg_date + "]";
	}
	
	
}
