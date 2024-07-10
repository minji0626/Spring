package kr.spring.board.vo;

import kr.spring.util.DurationFromNow;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardResponseVO {
	
	private long te_num;
	private String te_content;
	private long te_parent_num;
	private int te_depth;
	private String te_ip;
	private long re_num;
	private long mem_num;
	
	private String id;
	private String nick_name;
	private String parent_id;
	private String parent_name;
	
	private String te_date;
	private String te_mdate;
	
	
	public void setTe_date(String te_date) {
		this.te_date = DurationFromNow.getTimeDiffLabel(te_date);
	}
	
	public void setTe_mdate(String te_mdate) {
		this.te_mdate = DurationFromNow.getTimeDiffLabel(te_mdate);
	}
	
}
