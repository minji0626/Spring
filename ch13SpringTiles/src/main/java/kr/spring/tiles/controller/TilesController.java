package kr.spring.tiles.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesController {
	
	@RequestMapping("/")
	public String init() {
		return "redirect:/main.do";
	}
	
	@RequestMapping("/main.do")
	public String viewMain() {
		return "main";
	}
	@RequestMapping("/company.do")
	public String viewCompany() {
		return "company";
	}
	@RequestMapping("/product.do")
	public String viewProduct() {
		return "product";
	}
	
}
