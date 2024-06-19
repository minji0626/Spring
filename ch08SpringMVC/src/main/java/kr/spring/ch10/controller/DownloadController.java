package kr.spring.ch10.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
	
	@RequestMapping("/file.do")
	// session을 통해서 절대 경로를 받을 수 있음
	public ModelAndView download(HttpSession session) {
		// file.txt의 컨텍스 경로상의 절대 경로를 구하기
		String path = session.getServletContext().getRealPath("/WEB-INF/file.txt");
		// 경로를 구한 후 파일 객체를 생성
		File downloadFile = new File(path);
							//	뷰 이름		속성명		 속성값			
		return new ModelAndView("download","downloadFile",downloadFile);
		// 새로운 뷰 리솔버 등록
	}
	
}
