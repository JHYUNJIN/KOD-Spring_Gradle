package com.koreait.kod.controller.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateMembeGrade {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/updateMemberGrade")
	@LoginCheck(checkRole = Role.ADMIN)
	public String changeMemberGrade(List<MemberDTO> memberDatas,MemberDTO memberDTO,Model model,HttpSession session) {
		
//		MemberDTO adminDTO = (MemberDTO)session.getAttribute("adminDTO");
//		if(!adminDTO.getMemberRole().equals("ADMIN")) {
//			return "common/error";
//		}
		
		for (MemberDTO data : memberDatas) {
			data.setSearchCondition("updateMemberGrade");
			memberService.update(data);
		}
		
		return "admin/member/MemberList";
	}
}
