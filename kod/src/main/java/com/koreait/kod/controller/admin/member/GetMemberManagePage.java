package com.koreait.kod.controller.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class GetMemberManagePage {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/getMemberManagePage")
	@LoginCheck(checkRole = Role.ADMIN)
	public String getMemberManagePage(MemberDTO memberDTO,Model model) {
		
		// 총회원 수 , 이번달 누적 가입자 수, 금일 가입자 수, 이번달 탈퇴회원 누적 수 -> 누적 가입자 수를 알려면 테이블 수정이 필요함 -> 회원가입일 컬럼추가, 탈퇴일 컬럼추가
		model.addAttribute("memberDatas", memberService.selectAll(memberDTO));
		
		
		return "admin/member/memberManage";
	}
}
