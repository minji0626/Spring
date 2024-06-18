package kr.spring.ch08.vo;

import org.springframework.web.multipart.MultipartFile;

public class SubmitReportVO {
	private String subject;
	private MultipartFile reportFile;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public MultipartFile getReportFile() {
		return reportFile;
	}
	public void setReportFile(MultipartFile reportFile) {
		this.reportFile = reportFile;
	}
	@Override
	public String toString() {
		return "SubmitReportVO [subject=" + subject + ", reportFile=" + reportFile + "]";
	}
	
	
	
}
