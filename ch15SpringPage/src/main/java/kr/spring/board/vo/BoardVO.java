package kr.spring.board.vo;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	
	private Long board_num;
	
	// 문자열 길이 측정
	@NotBlank
	private String category;
	
	@NotBlank
	private String title;
	
	@NotEmpty
	private String content;
	
	private int hit;
	private Date reg_date;
	private Date modify_date;
	private String filename;
	private String ip;
	private Long mem_num;
	private MultipartFile upload;
	
	// 조인을 이용해서 가져올 데이터
	private String id;
	private String nick_name;
	
	private int re_cnt; // 댓글 개수
	private int fav_cnt; // 좋아요 개수
}
 