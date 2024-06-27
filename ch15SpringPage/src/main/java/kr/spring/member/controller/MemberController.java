package kr.spring.member.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
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
import kr.spring.util.CaptchaUtil;
import kr.spring.util.FileUtil;

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
	public String changePassword(@Valid MemberVO memberVO, HttpSession session, BindingResult result) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user == null) {
			return "memberLogin";
		} 
		
		if(result.hasFieldErrors("passwd")) {
			return formChangePassword();
		}
		
		memberVO.setMem_num(user.getMem_num());
		memberService.updatePassword(memberVO);
		
		return "redirect:/member/myPage";
	}
	
	
	// 네이버 캡챠 처리하기(api 사용)
	// 캡챠 이미지 호출
	@GetMapping("/member/getCaptcha")
	public String getCaptcha(Model model, HttpSession session) {
		// 강사님이 보내주신 정보
		String clientId = "HNtvT0vO0xB9fEP5czzq";
		String clientSecret = "853al0auHW";
		
		String code = "0";	// 키 발급시 0, 캡챠 이미지 비교시 1로 세팅
		String key_apiURL="https://openapi.naver.com/v1/captcha/nkey?code=" + code;
		
		Map<String,String> requestHeaders= new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		
		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);
		
		log.debug("<< responseBody >> : " + responseBody);
		
		JSONObject jObject = new JSONObject(responseBody);
		try {
			// key -> https://openapi.naver.com/v1/captcha/nkey 얘를 호출해서 받은 key 값을 의미
			String key = jObject.getString("key");
			// 캡챠 이미지와 동일하게 전송해야 하기 때문에 세션에 보관
			session.setAttribute("captcha_key", key);
			
			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
			
			Map<String,String> requestHeaders2= new HashMap<String, String>();
			requestHeaders.put("X-Naver-Client-Id", clientId);
			requestHeaders.put("X-Naver-Client-Secret", clientSecret);
			
			// byte 배열에 image 넣어주기 (불러온 정보들)
			byte[] response_byte = CaptchaUtil.getCaptchaImage(apiURL, requestHeaders2);
			
			model.addAttribute("imageFile",response_byte);
			model.addAttribute("filename","captcha.jpg");
			
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			
		}
		
		return "imageView";
	}
	
}
