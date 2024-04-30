package com.koreait.kod.controller.admin.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.coupon.CouponDTO;
import com.koreait.kod.biz.coupon.CouponService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

@Controller
public class GetCouponMemberListPage {

	@Autowired
	CouponService couponService;
	
	@GetMapping("/getCouponMemberListPage")
	@LoginCheck(checkRole = Role.ADMIN)
	public String getCouponMemberList(CouponDTO couponDTO,Model model) {
		
//		if(couponDTO.getCouponStatus().equals("unused")) {
//			couponDTO.setSearchCondition("unUsedCoupon");
//		}
//		else if(couponDTO.getCouponStatus().equals("used")) {
//			couponDTO.setSearchCondition("usedCoupon");
//		}
//		else { // 만료
//			couponDTO.setSearchCondition("expiredCoupon");
//		}
		
//		couponDTO.setSearchCondition("getCouponMemberList");
//		model.addAttribute("couponDatas", couponService.selectAll(couponDTO));
		
		return "admin/coupon/couponMemberList";
	}
}
