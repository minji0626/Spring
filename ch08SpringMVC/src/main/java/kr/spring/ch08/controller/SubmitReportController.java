package kr.spring.ch08.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch08.validator.SubmitReportValidator;
import kr.spring.ch08.vo.SubmitReportVO;

@Controller
public class SubmitReportController {
	
	//파일 업로드
	@Value("${file_path}")
	private String path;
	
	// 유효성 체크를 위한 자바빈 초기화
	@ModelAttribute("report")
	public SubmitReportVO initCommand() {
		return new SubmitReportVO();
	}
	
	// 폼 호출하기
	@GetMapping("/report/submitReport.do")
	public String form() {
		return "report/submitReportForm";
	}
	
	// 폼 등록하기
	@PostMapping("/report/submitReport.do")
	public String submit(@ModelAttribute("report") SubmitReportVO submitReportVO, BindingResult result) throws IllegalStateException, IOException {
		
		
		// 전송된 데이터 유효성 체크하기
		new SubmitReportValidator().validate(submitReportVO, result);
		// 유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return form();
		}
		// 정상적으로 파일 업로드에 성공하였을 경우
		File file = new File(path + "/" + submitReportVO.getReportFile().getOriginalFilename());
		// 지정한 경로에 파일을 저장하기
		submitReportVO.getReportFile().transferTo(file);
		
		System.out.println(submitReportVO);
		
		return "report/submittedReport";
	}
	
}
