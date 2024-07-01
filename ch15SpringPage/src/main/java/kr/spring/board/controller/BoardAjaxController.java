package kr.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardAjaxController {
	
	@Autowired
	private BoardService boardService;
	
	/*==================
	 * 부모글 처리하기 
	 *================== */
	// 업로드 파일 삭제하기
	@PostMapping("/board/deleteFile")
	@ResponseBody
	public Map<String, String> processFile(long board_num, HttpSession session, HttpServletRequest request){
		
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO) session.getAttribute("user");
		
		if(user == null) {
			mapJson.put("result", "logout");
		} else {
			BoardVO db_board = boardService.selectBoard(board_num);
			// 로그인한 회원 번호와 작성자 회원 번호 일치 여부 체크하기
			if(user.getMem_num() != db_board.getMem_num()) {
				mapJson.put("result","wrongAccess");
			} else {
				boardService.deleteFile(board_num);
				FileUtil.removeFile(request, db_board.getFilename());
			
				mapJson.put("result","success");
			}
		}
		
		return  mapJson;
	}
}
