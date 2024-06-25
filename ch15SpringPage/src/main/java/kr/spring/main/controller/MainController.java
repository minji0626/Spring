package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

// Lombok에 있는 어노테이션
@Slf4j
@Controller
public class MainController {
	
	@GetMapping("/")
	public String init() {
		return "redirect:/main/main";
	}
	
	@GetMapping("/main/main")
	public String main() {
		return "main"; // Tiles의 설정명
	}
}
