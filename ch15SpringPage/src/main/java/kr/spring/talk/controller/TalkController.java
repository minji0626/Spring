package kr.spring.talk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.talk.service.TalkService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TalkController {
	
	@Autowired
	private TalkService talkService;
	
	@Autowired
	private MemberService memberService;
	
	/*
	 * 채팅방 목록 가져오기
	 * 
	 * */
	@GetMapping("/talk/talkList")
	public String chatList(@RequestParam(defaultValue = "1") int pageNum, String keyword, HttpSession session, Model model) {
		
		return "talkList";
	}
	
	
	/*
	 * 채팅방 생성 폼 호출
	 * 
	 * */
	@GetMapping("/talk/talkRoomWrite")
	public String talkRoomWrite() {
		
		return "talkRoomWrite";
	}
	
	// 채팅 회원 검색
	@GetMapping("/talk/memberSearchAjax")
	@ResponseBody
	public Map<String, Object> memberSearchAjax(String id , HttpSession session) {
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user == null) {
			mapJson.put("result", "logout");
		} else {
			List<MemberVO> member = memberService.selectSearchMember(id);
			
			mapJson.put("result", "success");
			mapJson.put("member", member);
		}
		
		return mapJson;
	}
	
	
	
	
	
	
	
	
	
}
