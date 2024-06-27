package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberAjaxController {
	@Autowired
	private MemberService memberService;
	
	// ajax는 list 또는 map으로 반환
	// 값이 전달이 안된다면 오류가 나도록 requestparam 작성
	@GetMapping("/member/confirmId")
	@ResponseBody
	public Map<String,String> process(@RequestParam String id){
		log.debug("<<아이디 중복 체크>> : " + id);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO member = memberService.selectCheckMember(id);
		
		if(member != null) {
			// 아이디 중복
			mapAjax.put("result", "idDuplicated");
		} else {
			if(!Pattern.matches("^[a-zA-Z0-9]{4,12}$",id)) {
				mapAjax.put("result", "notMatchPattern");
			} else {
//				패턴이 일치하면서 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}
		
		return mapAjax;
	}
	
	// 프로필 사진 업로드 작업
	@PostMapping("/member/updateMyPhoto")
	@ResponseBody
	public Map<String, String> processProfile(MemberVO memberVO, HttpSession session){
		log.debug("<<프로필 사진 변경>> : " + memberVO);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user == null) {
			mapAjax.put("result", "logout");
		}
		else {
			memberVO.setMem_num(user.getMem_num());
			memberService.updateProfile(memberVO);
			
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
}
