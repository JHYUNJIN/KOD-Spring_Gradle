package com.koreait.kod.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinPage {

	@GetMapping("/joinPage")
	public String joinPage() {
		System.out.println("[로그:정현진] 회원가입 페이지 들어옴");
		
		
		return "user/join/join";
	}
}
