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

import kr.spring.member.email.Email;
import kr.spring.member.email.EmailSender;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberAjaxController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailSender	emailSender;
	
	@Autowired
	private Email email;
	
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
	
	@PostMapping("/member/getPasswordInfo")
	@ResponseBody
	public Map<String, String> sendEmailAction (MemberVO memberVO){
		
		log.debug("<< 비밀번호 찾기 >> : " + memberVO);
		
		Map<String,String> mapJson = new HashMap<String, String>();
		MemberVO member = memberService.selectCheckMember(memberVO.getId());
		
		// 오류를 대비해서 원래 비번 저장하기
		if(member != null && member.getAuth() > 1 && member.getEmail().equals(memberVO.getEmail())) {
			String origin_passwd = member.getPasswd();
			
			String passwd = StringUtil.randomPassword(10); // 10글자로 지정
			member.setPasswd(passwd);	// 임시비밀번호를 db에 저장
			memberService.updateRandomPassword(member);
			
			email.setContent("새로 발급한 임시 비밀번호는 " + passwd + " 입니다.");
			email.setReceiver(member.getEmail());
			email.setSubject(member.getId() + " 님 임시 비밀번호 메일입니다.");
			
			try {
				emailSender.sendEmail(email);
				mapJson.put("result", "success");
			} catch (Exception e) {
				log.error("<< 비밀 번호 찾기>>" + e.toString());
				
				// 오류 발생시 비밀번호 원상 복구
				member.setPasswd(origin_passwd);
				memberService.updateRandomPassword(member);
				mapJson.put("result", "failure");
			}
			
		} else if (member != null && member.getAuth() == 1) {
			//  정지 회원의 경우
			mapJson.put("result", "noAuthority");
		} else {
			mapJson.put("result", "invalidInfo");
		}
		
		return mapJson;
	}
	
}
