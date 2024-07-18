package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
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
import kr.spring.util.CaptchaUtil;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
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
				boolean autoLogin = memberVO.getAuto() != null && memberVO.getAuto().equals("on");
				if(autoLogin) {
					// 자동 로그인을 체크한 경우
					String au_id = member.getAu_id();
					
					if(au_id == null) {
						// 자동 로그인 체크 식별값을 생성해준다.
						au_id = UUID.randomUUID().toString();	// 랜덤 값으로 생성해줌
						log.debug(" << au_id >>  : " + au_id);
						member.setAu_id(au_id);
						memberService.updateAu_id(member.getAu_id(), member.getMem_num());
					}
					Cookie auto_cookie = new Cookie("au-log", au_id);
					auto_cookie.setMaxAge(60*60*24*7);	// 쿠키의 유효기간은 일주일로 설정
					auto_cookie.setPath("/");
					
					response.addCookie(auto_cookie);
				}
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
	public String processLogout(HttpSession session, HttpServletResponse response) {
		// 로그아웃 처리
		session.invalidate();
		
		// 자동 로그인 처리 해제 시작
		Cookie auto_cookie = new Cookie("au-log", "");
		auto_cookie.setMaxAge(0);	// 쿠키 삭제하기
		auto_cookie.setPath("/");
		
		response.addCookie(auto_cookie);
		// 자동 로그인 처리 해제 종료
		
		log.debug("<< 로그아웃이 완료되었습니다! >>");
		return "redirect:/main/main";
	}
	
	@GetMapping("/member/myPage")
	public String process(HttpSession session, Model model) {
		// 로그인 한 사람의 회원 정보(session)를 가져온다
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MemberVO member = memberService.selectMember(user.getMem_num());
		log.debug("<< My Page >> : " + member);
		
		model.addAttribute("member",member);
		return "myPage";
	}
	
	// 회원 정보 수정하기
	// 수정 폼 호출
	@GetMapping("/member/update")
	public String formUpdate(HttpSession session, Model model) {
		// 세션에 저장된 user의 정보를 가져온다
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		model.addAttribute("memberVO",memberVO);
		
		return "memberModify";
	}
	
	// 회원 정보 수정
	@PostMapping("/member/update")
	public String postMethodName(@Valid MemberVO memberVO, BindingResult result,HttpSession session) {
		
		log.debug("<< 회원 정보 수정 >> : " + memberVO);
		
		if(result.hasErrors()) {
			return "memberModify";
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		memberService.updateMember(memberVO);
		
		// 세션에 저장된 정보 변경
		user.setNick_name(memberVO.getNick_name());
		user.setEmail(memberVO.getEmail());
		
		return "redirect:/member/myPage";
	}
	
	// 프로필 사진 출력하기(로그인 전용)
	// 이미지 스트림을 처리할 수 있는 클래스 -> imageView
	// 해당 이름하고 동일한 클래스를 만들어서 스트림 처리를 진행 -> class가 view 역할을 할 수 있게
	@GetMapping("/member/photoView")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		// session에 저장된 user의 정보를 불러온다
		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<< 프로필 사진 출력 >> : " + user);
		
		
		if(user == null) {
			// 로그인이 되지 않은 경우
			getBasicProfileImage(request, model);
		} else {
			// 로그인이 된 경우
			// user를 통해서 mem_num을 받아와서 데이터를 읽어와서 처리한다
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO, request, model);
		}
		
		return "imageView";
	}
	
	// 프초필 사진 출력하기
	@GetMapping("/member/viewProfile")
	public String getProfileByMem_num(long mem_num, HttpServletRequest request, Model model) {
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		viewProfile(memberVO, request, model);
		
		return "imageView";
	}
	
	// 회원 프로필 사진 처리를 위한 공통 코드
	// 이미지가 없는 경우 -> basicprofileimage 메서드를 호출한다
	// 이미지가 있는 경우 -> imagefile과 filename을 읽어온다
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if (memberVO == null || memberVO.getPhoto_name() ==  null) {
			// DB에 저장된 프로필 사진이 없기 때문에 기본 이미지를 띄운다.
			getBasicProfileImage(request, model);
		} else {
			// 업로드된 프로필 이미지 읽어오기
			model.addAttribute("imageFile", memberVO.getPhoto());
			model.addAttribute("filename", memberVO.getPhoto_name());
		}
	}
	
	// 기본 이미지 읽어오기
	// DB에 저장되어있는 프로필 이미지가 없고, 오류가 있어서 보여지는 상황의 경우에는 기본 이미지를 보여준다
	// 기본이미지는 image_bundle 폴더의 face.png이다.
	// 로그인이 갑자기 풀린 경우 정보가 빠져나가지 않게 해주기도 한다.
	public void getBasicProfileImage(HttpServletRequest request, Model model) {
																		// 실제 경로를 지정
		byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
		model.addAttribute("imageFile", readbyte);
		model.addAttribute("filename", "face.png");
	}
	
	// 회원 비밀번호 변경 폼
	@GetMapping("/member/changePassword")
	public String formChangePassword() {
		return "memberChangePassword";
	}
	
	// 회원 비밀번호 변경하기
	@PostMapping("/member/changePassword")
	public String submitChangePassword(@Valid MemberVO memberVO,BindingResult result, HttpSession session,  Model model, HttpServletRequest request) {
		log.debug("<< 비밀번호 변경 처리 >> : " + memberVO);
		
		// 유효성 체크 결과 오류 있다면 폼 호출하기
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd") || result.hasFieldErrors("captcha_chars")) {
			return formChangePassword();
		}
		
		// 캡챠 문자 체크 시작
		// 캡챠 이미지 비교시 1로 세팅
		String code = "1";	
		// 캡챠 키 발급시 받은 키 값을 session에 저장했기 때문에 불러오기 가능
		String key=(String)session.getAttribute("captcha_key"); 
		// 사용자가 입력한 캡챠 이미지 글자값 
		String value = memberVO.getCaptcha_chars();
		// code, key, value 값을 모두 넣어준다
		String key_apiURL="https://openapi.naver.com/v1/captcha/nkey?code="+code+"&key="+ key+"&value="+value;
		
		Map<String,String> requestHeaders= new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", "HNtvT0vO0xB9fEP5czzq");
		requestHeaders.put("X-Naver-Client-Secret", "853al0auHW");
		
		// 값을 전달받기	// apiURL, requestHeaders의 값을 전달
		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);
		
		log.debug("<< 캡챠 결과 >> : " + responseBody);
		
		// json object로 변환 시켜준다
		JSONObject jObject = new JSONObject(responseBody);
		// 캡챠의 결과를 불리언 값으로 받는다
		boolean captcha_result = jObject.getBoolean("result");
		// 만약 일치하지 않았다면 invalidCaptcha 에러 코드를 생성한다.
		if(!captcha_result) {
			result.rejectValue("captcha_chars", "invalidCaptcha");
		}
		// 캡챠 문자 체크 종료
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		MemberVO db_member = memberService.selectMember(memberVO.getMem_num());
		
		// form 에서 전송한 현재 비번이랑 db에서 읽어온 비번이랑 일치 여부 체크하기
		if(!db_member.getPasswd().equals(memberVO.getNow_passwd())) {
			result.rejectValue("now_passwd", "invalidPassword");
			return formChangePassword();
		}
		// 비밀번호 수정
		memberService.updatePassword(memberVO);
		
		// 설정되어있는 자동 로그인 기능 해제한다()
		memberService.deleteAu_id(memberVO.getMem_num());
		//  UI 문구 처리
		model.addAttribute("message","비밀번호 변경이 완료되었습니다. 재접속시 설정되어있는 자동로그인 기능이 해제됩니다.");
		model.addAttribute("url", request.getContextPath()+"/member/myPage");
		
		return "common/resultAlert";
	}
	
	
	// 네이버 캡챠 처리하기(api 사용)
	// 캡챠 이미지 호출
	@GetMapping("/member/getCaptcha")
	public String getCaptcha(Model model, HttpSession session) {
		
		String code = "0";	// 키 발급시 0, 캡챠 이미지 비교시 1로 세팅
		String key_apiURL="https://openapi.naver.com/v1/captcha/nkey?code=" + code;
		
		Map<String,String> requestHeaders= new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", "HNtvT0vO0xB9fEP5czzq");
		requestHeaders.put("X-Naver-Client-Secret", "853al0auHW");
		
		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);
		
		log.debug("<< responseBody >> : " + responseBody);
		
		JSONObject jObject = new JSONObject(responseBody);
		try {
			// key -> https://openapi.naver.com/v1/captcha/nkey 얘를 호출해서 받은 key 값을 의미
			String key = jObject.getString("key");
			// 캡챠 이미지와 동일하게 전송해야 하기 때문에 세션에 보관
			session.setAttribute("captcha_key", key);
			
			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
			
			// byte 배열에 image 넣어주기 (불러온 정보들)
			byte[] response_byte = CaptchaUtil.getCaptchaImage(apiURL, requestHeaders);
			
			model.addAttribute("imageFile",response_byte);
			model.addAttribute("filename","captcha.jpg");
			
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			
		}
		
		return "imageView";
	}
	
	
	// 비밀번호 찾기
	@GetMapping("/member/sendPassword")
	public String sendPasswordForm() {
		return "memberFindPassword";
	}
	
	
}
