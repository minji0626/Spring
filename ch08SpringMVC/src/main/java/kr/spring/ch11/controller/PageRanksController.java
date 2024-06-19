package kr.spring.ch11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch11.vo.PageRank;

@Controller
public class PageRanksController {
		
		// 엑셀 다운로드
		@RequestMapping("/pageRanksExcel.do")
		public ModelAndView handle() {
			List<PageRank> pageRanks = new ArrayList<PageRank>();
			pageRanks.add(new PageRank(1,"/board/list.do")); // 1위
			pageRanks.add(new PageRank(2,"/member/login.do")); // 2위
			pageRanks.add(new PageRank(3,"/cart/list.do")); // 3위
			
								// 뷰 이름		속성명		속성값
			return new ModelAndView("pageRanks", "pageRank" ,pageRanks);
		}
		
		// JSON 문자열 처리하기
		@RequestMapping("/pageJson.do")
		// view를 자동으로 생성해준다. list, map으로 만들어주면 JSON문자열로 처리를 자동해주는 어노테이션
		@ResponseBody
		public List<PageRank> parseJson() {
			List<PageRank> pageRanks = new ArrayList<PageRank>();
			pageRanks.add(new PageRank(1,"/file.do"));
			pageRanks.add(new PageRank(2,"/pageRanksExcel.do"));
			pageRanks.add(new PageRank(3,"/pageJson.do"));
			
			return pageRanks;
		}
		
}
