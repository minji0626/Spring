package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class MemberController {
		
		@Autowired
		public MemberService memberService;
		
		//로그 처리(로그 대상 지정)
		private static final Logger log = 
				LoggerFactory.getLogger(MemberController.class);
		
		// 유효성 체크를 위한 자바빈 생성하기
		@ModelAttribute("member")
		public MemberVO initCommand() {
			return new MemberVO();
		}
	
		// 회원가입 폼
		@GetMapping("/newAccount.do")
		public String newAccountForm() {
			return "views/newaccountForm"; 
		}
		
		// 회원가입 등록
		@PostMapping("/newAccount.do")
		public String submitnewAccount(
				@ModelAttribute("member")@Valid MemberVO vo, BindingResult result) {
			log.debug("<<MemberVO>> : " + vo);
			
			if(result.hasErrors()) {
				return "views/newaccountForm";
			}
			
			memberService.insertMember(vo);
			
			return "redirect:/memberList.do";
		}
		
		// 회원 목록 불러오기
		@RequestMapping("/memberList.do")
		public ModelAndView getmemberList(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {
			
			int count = memberService.selectMemberCount();
			PagingUtil page = new PagingUtil(currentPage, count, 20,10,"memberList.do");
			
			List<MemberVO> list = null;
			
			if(count > 0) {
				Map<String , Integer> map = new HashMap<String, Integer>();
				
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());
				
				list = memberService.selectMemberList(map);
			}
			ModelAndView mav = new ModelAndView();
			//뷰 이름 설정
			mav.setViewName("views/memberList");
			//데이터 저장
			mav.addObject("count", count);
			mav.addObject("list", list);
			mav.addObject("page", page.getPage());
			
			return mav;
		}
		
		// 회원 상세 불러오기
		@RequestMapping("/memberDetail.do")
		public ModelAndView memberDetail(int num) {
			MemberVO membervo = memberService.selectMember(num);
			return new ModelAndView("views/memberDetail","membervo",membervo);
		}
		
		// 회원 정보 수정 폼
		@GetMapping("/memberUpdate.do")
		public String memberUpdateForm(int num, Model model) {
			model.addAttribute("member", memberService.selectMember(num));
			return "views/memberUpdateForm";
		}
		// 회원 정보 수정
		@PostMapping("/memberUpdate.do")
		public String memberUpdate(@ModelAttribute("member") @Valid MemberVO membervo, BindingResult result) {
			log.debug("<<MemberVO>> : " + membervo);
			
			if(result.hasErrors()) {
				return "views/memberUpdateForm";
			}
			
			MemberVO db_member = memberService.selectMember(membervo.getNum());
			
			if(!db_member.getPasswd().equals(membervo.getPasswd())) {
				result.rejectValue("passwd", "invalidPassword");
				return "views/memberUpdateForm";
			}
			
			// 회원 정보 수정하기
			memberService.updateMember(membervo);
			
			return "redirect:/memberList.do";
		}
		
		// 회원 정보 삭제 폼
		@GetMapping("/memberDelete.do")
		public String memberDeleteForm(@ModelAttribute("member") MemberVO membervo) {
			log.debug("<<MemberVO>> : " + membervo);
			return "views/memberDeleteForm";
		}
		
		// 회원 정보 삭제
		@PostMapping("/memberDelete.do")
		public String submitmemberDelete(@ModelAttribute("member") @Valid MemberVO membervo, 
											BindingResult result) {
			
			log.debug("<<MemberVO>> : " + membervo);
			
			if(result.hasFieldErrors("passwd")) {
				return "views/memberDeleteForm";
			}
			
			MemberVO db_member = memberService.selectMember(membervo.getNum());
			if(!db_member.getPasswd().equals(membervo.getPasswd())) {
				result.rejectValue("passwd", "invalidPassword");
				return "views/memberDeleteForm";
			}
			
			memberService.deleteMember(membervo.getNum());
			
			return "redirect:/memberList.do";
		}
		
}
