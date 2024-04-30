package com.koreait.kod.controller.admin.coupon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class DeleteCoupon {
	
	CouponService couponService;
	
	@PostMapping("/deleteCoupon")
	@LoginCheck(checkRole = Role.ADMIN)
	public String deleteCoupon(CouponDTO couponDTO,Model model) {
		
		if(!couponService.delete(couponDTO)) {
			System.out.println("[로그:정현진] 쿠폰삭제 실패");
		}
		System.out.println("[로그:정현진] 쿠폰삭제 성공");
		
		return "admin/coupon/couponList";
	}
}
