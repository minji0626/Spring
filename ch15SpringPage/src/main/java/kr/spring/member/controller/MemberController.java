package kr.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

@Controller
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	// 회원 가입 - 자바빈 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	// 회원 가입 - 폼 호출
	@GetMapping("/member/registerUser")
	public String form() {
		return "memberRegister";
	}
	
	@PostMapping("/member/registerUser")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model, HttpServletRequest request) {
		log.debug("<<회원 가입>> : " + memberVO);
		
		if(result.hasErrors()) {
			return form();
		}
		
		memberService.insertMember(memberVO);
		
		//  UI 문구 처리
		model.addAttribute("accessTitle","회원 가입");
		model.addAttribute("accessMsg","회원 가입이 완료되었습니다");
		model.addAttribute("accessBtn","홈으로");
		model.addAttribute("accessUrl", request.getContextPath()+"/main/main");
		
		return "common/resultView";
	}
	
	// 회원 로그인 폼 호출하기
	@GetMapping("/member/login")
	public String formLogin() {
		return "memberLogin";
	}
	
	// 회원 로그인 처리하기
	@PostMapping("/member/login")
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, HttpSession session,HttpServletResponse response ) {
		
		log.debug("<< 로그인 >> : " + memberVO);
		
		// 유효성 체크 결과 오류가 있다면 form 호출
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		// 아이디 비밀번호 일치 여부 체크하기
		MemberVO member = null;
		
		try {
			member = memberService.selectCheckMember(memberVO.getId());
			
			boolean check = false;
			
			if(member != null) { // 아이디 존재함
				// 비밀번호 일치 여부 체크
				check = member.ischeckedPassword(memberVO.getPasswd());
			}
			// true 라면 if(check) 내부로 진입함
			if(check) { // 인증 성공
				// 자동 로그인 체크 시작
				
				// 자동 로그인 체크 종료
				
				// 멤버 확인하여 로그인 처리
				// session에다가 자바빈을 통채로 집어넣기
				session.setAttribute("user", member);
				
				log.debug("<< 인증 성공! >>");
				log.debug("<< ID >> : " + member.getId());
				log.debug("<< Au_ID >> : " + member.getAu_id());
				log.debug("<< Auth >> : " + member.getAuth());
				
				if(member.getAuth() == 9) {
					// 관리자라면
					return"redirect:/main/admin";
				} else {
					return"redirect:/main/main";
				}
			}
			// 인증 실패
			throw new AuthCheckException();
		} 
		catch (AuthCheckException e) {
			// 인증 실패 로그인 폼 호출하기
			
			if(member != null && member.getAuth() == 1) {
				// 정지회원 메세지 표시하기
				result.reject("noAuthority");
			} else {
				result.reject("invalidIdOrPassword");
			}
			log.debug("<< 인증에 실패하였습니다. >>");
		}
		return formLogin();	
	}
	
	// 로그아웃 처리하기
	@GetMapping("/member/logout")
	public String processLogout(HttpSession session) {
		// 로그아웃 처리
		session.invalidate();
		
		// 자동 로그인 처리 해제 시작
		
		// 자동 로그인 처리 해제 종료
		
		log.debug("<< 로그아웃이 완료되었습니다! >>");
		return "redirect:/main/main";
	}
	
		
}
