package com.koreait.kod.controller.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetMemberRecoveryPage {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/getMemberRecoveryPage")
	public String getMemberRecoveryPage(MemberDTO memberDTO, Model model,HttpSession session) {
		
		memberDTO.setSearchCondition("requestMemberRecoveryList");
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		
		return "admin/member/memberRecoveryPage";
	}
}
