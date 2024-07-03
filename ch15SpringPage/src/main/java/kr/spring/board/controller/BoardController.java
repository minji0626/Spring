package kr.spring.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
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
	
	
	/* ====================
	 * 게시판 목록 불러오기
	 * ====================
	 * */
	@GetMapping("/board/list")
	public String getList(@RequestParam(defaultValue = "1") int pageNum, 
							@RequestParam(defaultValue = "1") int order, @RequestParam(defaultValue = "") String category,
							String keyfield, String keyword, Model model) {
		log.debug("<< 게시판 목록 - category >> : " + category);
		log.debug("<< 게시판 목록 - order >> : " + order);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("category", category);
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		int count = boardService.selectRowCount(map);
		
		PagingUtil page = new PagingUtil(keyfield,keyword,pageNum,count,20,10,"list","&category="+category+"&order="+order);
		
		List<BoardVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			map.put("order", order);
			
			list = boardService.selectList(map);
		}
		
		model.addAttribute("list", list);
	    model.addAttribute("count", count);
	    model.addAttribute("page", page.getPage());
		
		return "boardList";
	}
	
	
	/* =======================
	 * 게시판 글 상세 불러오기
	 * =======================
	 */
	
	@GetMapping("/board/detail")
	public ModelAndView process(long board_num) {
		
		log.debug("<< 게시판 번호 >> : " + board_num);
		
		// 해당 글의 조회수 증가시키기
		boardService.updateHit(board_num);
		
		BoardVO board = boardService.selectBoard(board_num);
		
		// 제목에 태그를 허용하지 않는다
		board.setTitle(StringUtil.useNoHTML(board.getTitle()));
		
		//board.setContent(StringUtil.useBrNoHTML(board.getContent()));
		
		
		return new ModelAndView("boardView","board",board);
	}

	// 파일 다운로드하기
	@GetMapping("/board/file")
	public String download(long board_num, HttpServletRequest request, Model model) {
		
		BoardVO board = boardService.selectBoard(board_num);
		byte[] downloadFile = FileUtil.getBytes(request.getServletContext().getRealPath("/upload")+"/"+board.getFilename());
		model.addAttribute("downloadFile",downloadFile);
		model.addAttribute("filename", board.getFilename());
		return "downloadView";
	}
	
	// 게시판 글 수정하기
	// 수정폼
	@GetMapping("/board/update")
	public String formUpdate(long board_num, Model model) {
		
		BoardVO boardVO = boardService.selectBoard(board_num);
		
		model.addAttribute("boardVO",boardVO);
		return "boardModify";
	}
	
	// 수정하기
	@PostMapping("/board/update")
	public String submitUpdate(@Valid BoardVO boardVO, BindingResult result, HttpSession session,HttpServletRequest request,Model model) throws IllegalStateException, IOException {
		
		log.debug("<< 글 업데이트 >> : " + boardVO);
		
		if(result.hasErrors()) {
			// title, content 가 입력되지 않아서 유효성 체크에 걸리면 파일 정보를 잃어버린다.
			BoardVO vo = boardService.selectBoard(boardVO.getBoard_num());
			boardVO.setFilename(vo.getFilename());
			return "boardModify";
		}
		
		BoardVO db_board = boardService.selectBoard(boardVO.getBoard_num());
		boardVO.setFilename(FileUtil.createFile(request, boardVO.getUpload()));
		
		// IP 세팅하기
		boardVO.setIp(request.getRemoteAddr());
		
		boardService.updateBoard(boardVO);
		
		if(boardVO.getUpload() != null && !boardVO.getUpload().isEmpty()) {
			FileUtil.removeFile(request, db_board.getFilename());
		}
		
		//  UI 문구 처리
		model.addAttribute("message","글 수정이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/board/detail?board_num="+boardVO.getBoard_num());
				
		return "common/resultAlert";
	}
	
	
	/* =======================
	 * 게시판 글 삭제 
	 * =======================
	 */
	
	@GetMapping("/board/delete")
	public String submitDelete(long board_num, HttpServletRequest request, Model model) {
		
		log.debug("<< 게시판 글 삭제 >> :" + board_num);
		
		// db에 저장된 파일 정보 구하기
		BoardVO db_board = boardService.selectBoard(board_num);
		
		// 글 삭제
		boardService.deleteBoard(board_num);
		
		if(db_board.getFilename() != null) {
			FileUtil.removeFile(request, db_board.getFilename());
		}
		
		//  UI 문구 처리
		model.addAttribute("message","글 수정이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/board/list");
					
		return "common/resultAlert";
	}
	
	
}
