package com.koreait.kod.controller.admin.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetProductSalesStatisticsByQuarter {
	@Autowired
	ProductService	productService;

	@GetMapping("/getProductSalesStatisticsByQuarterPage")
	@LoginCheck(checkRole = Role.ADMIN)
	public String getProductSalesStatisticsByQuarter(ProductDTO productDTO,Model model,MemberDTO adminDTO,HttpSession session) {
		
//		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
//			return "common/error";
//		}
		
		productDTO.setSearchCondition("quarterlyStatistics");
		model.addAttribute("quarterlyDatas", productService.selectAll(productDTO));
		
		productDTO.setSearchCondition("orderCntAndRevenue");
		model.addAttribute("orderCntAndRevenueDatas", productService.selectAll(productDTO));
		
		return "admin/statistics/productSalesStatisticsByQuarter";
	}
}
