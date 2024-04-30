package com.koreait.kod.controller.admin.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetIssuedCouponList {
	
	@Autowired
	CouponService couponService;

	@GetMapping("/getIssuedCouponList") // 발행된 쿠폰목록 반환
	@LoginCheck(checkRole = Role.ADMIN)
	public String getIssuedCouponList(CouponDTO couponDTO,Model model,HttpSession session) {
		System.out.println("[로그:정현진] 쿠폰목록 페이지 들어옴");
		
//		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
//			return "common/error";
//		}
		System.out.println("[로그:정현진] "+couponDTO.getCouponStatus());
		couponDTO.setSearchCondition("isuuedCouponList");
		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		return "admin/coupon/issuedCouponList";
	}
	
	
}
