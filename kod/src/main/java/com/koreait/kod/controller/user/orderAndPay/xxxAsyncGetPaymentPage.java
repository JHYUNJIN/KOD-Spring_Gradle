//package com.koreait.kod.controller.user.orderAndPay;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.koreait.kod.biz.address.AddressDTO;
//import com.koreait.kod.biz.address.AddressService;
//import com.koreait.kod.biz.order.OrderContentDTO;
//import com.koreait.kod.biz.order.OrderContentService;
//import com.koreait.kod.biz.order.OrderListDTO;
//import com.koreait.kod.biz.order.OrderListService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class xxxAsyncGetPaymentPage {
//	
//	@Autowired
//	OrderListService orderListService;
//	@Autowired
//	OrderContentService orderContentService;
//	@Autowired
//	AddressService addressService;
//
//	@GetMapping("/asyncPayment")
//	public String getPaymentPage(OrderListDTO orderListDTO,OrderContentDTO orderContentDTO,AddressDTO addressDTO,Model model,HttpSession session) {
//		
//		/*
//		 * 결제 로직
//		 * orderInfoPage -> (구매하기 클릭) -> payment.jsp(QR로딩) -> 결제(QR찍음) -> AsyncPayment(결제로직 수행) -> payment.jsp로 반환 -> 결제성공 -> 결제정보페이지
//		 * 
//		 */
//		
//		
//		
//		
//		
//		
//		
//		// 주문번호 반환받기
//		orderListDTO.setMemberID((String)session.getAttribute("memberID"));
//		int orderNumber = orderListService.selectOne(orderListDTO).getOrderListID();
//
//		// 주문내역 반환받기
//		orderContentDTO.setMemberID((String)session.getAttribute("memberID"));
//		orderContentDTO.setOrderListID(orderNumber);
//		model.addAttribute("orderInfoDatas", orderContentService.selectAll(orderContentDTO));
//		
////		// 배송지 반환받기
////		addressDTO.setMemberID((String)session.getAttribute("memberID"));
////		model.addAttribute("addressData", addressService.selectOne(addressDTO));
//		
//		
//		
//		
//		
//		
//		
//		
//		return "";
//	}
//}
