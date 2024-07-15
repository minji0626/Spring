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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.talk.service.TalkService;
import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;
import kr.spring.util.PagingUtil;
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
		MemberVO user = (MemberVO)session.getAttribute("user");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("mem_num", user.getMem_num());

		int count = talkService.selectRowCount(map);

		// 페이지 처리하기
		PagingUtil page = new PagingUtil(null, keyword, pageNum,count,30,10,"talkList");

		List<TalkRoomVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = talkService.selectTalkRoomList(map);
		}
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("page", page.getPage());

		return "talkList";
	}


	/*
	 * 채팅방 생성 폼 호출
	 * */
	@GetMapping("/talk/talkRoomWrite")
	public String talkRoomWrite() {

		return "talkRoomWrite";
	}



	//	전송된 데이터 처리하기
	@PostMapping("/talk/talkRoomWrite")
	public String talkRoomSubmit(TalkRoomVO vo, HttpSession session) {

		log.debug(" << 채팅방 생성 >> : " + vo);
		MemberVO user = (MemberVO)session.getAttribute("user");

		// 채팅 멤버 초대 문구 설정하기
		vo.setTalkVO(new TalkVO());
		vo.getTalkVO().setMem_num(user.getMem_num());
		vo.getTalkVO().setMessage(user.getId() + "님이 " + findMemberId(vo, user) + "님을 초대했습니다. @{member}@");

		talkService.insertTalkRoom(vo);

		return "redirect:/talk/talkList";
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
	
	
//	채팅 메세지 처리하기
	@GetMapping("/talk/talkDetail")
	public String talkDetail(long talkroom_num, Model model, HttpSession session) {
		
		String chatMember = "";
		String room_name = "";
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		List<TalkMemberVO> list = talkService.selectTalkMember(talkroom_num);
		
		for(int i = 0; i<list.size(); i++) {
			TalkMemberVO vo = list.get(i);
			// 로그인한 회원의 채팅방 이름 세팅하기
			if(user.getMem_num() == vo.getMem_num()) {
				room_name = vo.getRoom_name();
			}
			// 채팅 멤버 저장하기
			if(i > 0 ) {	// 하나의 문장으로 만들기 위해for 문의 순서대로 아이디를 뽑아내고 , 도 끊어주는
				chatMember += ",";
			}
			chatMember += list.get(i).getId();
		}
		
		// 채팅 멤버의 id
		model.addAttribute("chatMember", chatMember);
		// 채팅 멤버의 수 - 리스트의 사이즈로 멤버수를 지정함
		model.addAttribute("chatCount",list.size());
		// 로그인한 회원의 채팅방 이름
		model.addAttribute("room_name", room_name);
		return "talkDetail";
	}
	

	//초대한 회원의 id 구하기
	private String findMemberId(TalkRoomVO vo,MemberVO user) {
		String member_id = "";
		long[] members = vo.getMembers();
		for(int i=0;i<members.length;i++) {
			String temp_id = memberService.selectMember(members[i]).getId();
			//초대한 사람의 아이디는 제외
			if(!user.getId().equals(temp_id)) {
				member_id += temp_id;
				if(i < members.length-1) member_id += ", ";
			}
		}
		return member_id;
	}




}
