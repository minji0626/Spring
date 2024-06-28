package kr.spring.board.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	public BoardService boardService;
	
	// 자바빈 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	/* ==============
	 * 게시판 글 작성
	 * ==============
	 * */
	// 게시판 등록 폼 호출
	@GetMapping("/board/write")
	public String form() {
		return "boardWrite";
	}
	
	// 게시판 등록하기
	@PostMapping("/board/write")
	public String submitInsert(@Valid BoardVO boardVO, BindingResult result, HttpSession session, Model model, 
								HttpServletRequest request) throws IllegalStateException, IOException {
	log.debug("<< 게시판 글 저장 >> : " + boardVO);
	
	if(result.hasErrors()) {
		return form();
	}
	
	// 회원 번호 세팅하기
	MemberVO user = (MemberVO)session.getAttribute("user");
	boardVO.setMem_num(user.getMem_num());
	// IP 세팅하기
	boardVO.setIp(request.getRemoteAddr());
	// 파일 업로드하기
	boardVO.setFilename(FileUtil.createFile(request, boardVO.getUpload()));
	
	boardService.insertBoard(boardVO);
		
	//  UI 문구 처리
	model.addAttribute("message","글 작성이 완료되었습니다.");
	model.addAttribute("url", request.getContextPath()+"/board/list");
			
	return "common/resultAlert";
	}
	
	
	/* =============
	 * 게시판 목록
	 * =============
	 * */
	@GetMapping("/board/list")
	public String getList(@RequestParam(defaultValue = "1") int pageNum, 
							@RequestParam(defaultValue = "1") int order, @RequestParam(defaultValue = "") String category,
							String keyfield, String keyword, Model model) {
		log.debug("<< 게시판 목록 - category >> : " + category);
		log.debug("<< 게시판 목록 - order >> : " + order);
		
		return "boardList";
	}
}
