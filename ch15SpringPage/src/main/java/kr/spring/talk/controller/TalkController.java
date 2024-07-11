package kr.spring.talk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	
	
	
	
	
	
	
	
}
