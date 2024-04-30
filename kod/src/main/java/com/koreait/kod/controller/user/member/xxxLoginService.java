//package com.koreait.kod.controller.user.member;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Scanner;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.koreait.kod.biz.member.MemberDTO;
//import com.koreait.kod.biz.member.MemberService;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//public class xxxLoginService {
//	
//	@Autowired
//	MemberService memberService;
//
//	public String getAccessToken(String authorize_code) { // authorize_code : mOpw6ZSnQ2FMB_gUHNma3PKQ__mzA_NVIjAKPXSYAAABjoO47gP_A_o_BVb6-Q
//
//		System.out.println("[로그:정현진] getAccessToken 들어옴");
//		
//		String access_Token = "";
//	    String refresh_Token = "";
//	    String reqURL = "https://kauth.kakao.com/oauth/token"; // 해당 주소로 토큰 요청하기
//	    
//	    try {
//	        URL url = new URL(reqURL);
//	        System.out.println("[로그:정현진] url : "+url);
//	        // url : https://kauth.kakao.com/oauth/token
//
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        System.out.println("[로그:정현진] conn : "+conn);
//	        // conn : sun.net.www.protocol.https.DelegateHttpsURLConnection:https://kauth.kakao.com/oauth/token
//
//	        //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로 변경을 해주세요
//
//	        conn.setRequestMethod("POST");
//	        conn.setDoOutput(true);
//
//	        // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//	        // BufferedWriter 간단하게 파일을 끊어서 보내기로 토큰값을 받아오기위해 전송
//
//	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//	        System.out.println("[로그:정현진] bw.toString() : "+bw.toString()); // bw : java.io.BufferedWriter@2c04c11a
//	        StringBuilder sb = new StringBuilder();
//	        sb.append("grant_type=authorization_code");
//	        sb.append("&client_id=a20b0a5327457efd072d19a6a022a4fe");  //발급받은 key == REST API 키
//	        sb.append("&redirect_uri=http://localhost:8088/auth_kakao");     // 본인이 설정해 놓은 redirect_uri 주소
//	        sb.append("&code=" + authorize_code);
//	        System.out.println("[로그:정현진] authorize_code : "+authorize_code); // authorize_code : r31c7t4EvViVsn7DIH9TAbHNj4NCXVi-JPUKKiWQAAABjoPBvQf_A_o_BVb6-Q
//	        System.out.println("[로그:정현진] sb : "+sb); // sb : grant_type=authorization_code&client_id=a20b0a5327457efd072d19a6a022a4fe&redirect_uri=http://localhost:8088/auth_kakao&code=6hhHkiagC4GdN6MLBysPTBupwZnsKOBsADcKKiURAAABjoPbsIX_A_o_BVb6-Q
//	        bw.write(sb.toString());
//	        bw.flush();
//
//	        //    결과 코드가 200이라면 성공
//	        // 여기서 안되는경우가 많이 있어서 필수 확인 !! **
//	        int responseCode = conn.getResponseCode();
//	        System.out.println("responseCode : " + responseCode + "확인");
//
//	        //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//	        System.out.println("[로그:정현진] conn : "+conn);
//	        InputStream stream = conn.getErrorStream();
//			if (stream != null) {
//				String response;
//				try (Scanner scanner = new Scanner(stream)) {
//					scanner.useDelimiter("\\Z");
//					response = scanner.next();
//				}
//				System.out.println("error response : " + response);
//			}
//	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        // 만약 응답 코드가 200인 경우(즉, OK)에만 conn.getInputStream()을 호출하여 응답 본문을 읽음 -> 응답이 200이 아니면 getInputStream()메소드가 실행되지 않는다.
//	        String line = "";
//	        String result = "";
//
//	        while ((line = br.readLine()) != null) {
//	            result += line;
//	        }
//	        System.out.println("response body : " + result + "결과");
//
//	        // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//	        JsonParser parser = new JsonParser();
//	        JsonElement element = parser.parse(result);
//
//	        System.out.println("[로그:정현진] element : "+element);
//	        access_Token = element.getAsJsonObject().get("access_token").getAsString();
//	        refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//
//	        System.out.println("access_token : " + access_Token);
//	        System.out.println("refresh_token : " + refresh_Token);
//
//	        br.close();
//	        bw.close();
//	    } catch (IOException e) {
//
//	        e.printStackTrace();
//	    }
//	    return access_Token;
//	}
//	
//	
//	 public String getuserinfo(String access_Token, HttpSession session, RedirectAttributes rttr) {
//		 
//		 System.out.println("[로그:정현진] getuserinfo 들어옴");
//		 
//		    HashMap<String, Object> userInfo = new HashMap<>();
////		    log.info("getuserinfo()");
//
//		    String requestURL = "https://kapi.kakao.com/v2/user/me";
//		    String view = null;
//		    String msg = null;
//
//		    try {
//		        URL url = new URL(requestURL); //1.url 객체만들기
//		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		        System.out.println("[로그:정현진] HttpURLConnection conn : "+conn);
//		        //2.url 에서 url connection 만들기
//		        conn.setRequestMethod("GET"); // 3.URL 연결구성
//		        System.out.println("[로그:정현진] access_Token : "+access_Token);
//		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//
//		        //키 값, 속성 적용
//		        int responseCode = conn.getResponseCode(); //서버에서 보낸 http 상태코드 반환
//		        System.out.println("responseCode :" + responseCode + "여긴가");
//		        BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		        // 버퍼를 사용하여 읽은 것
//		        String line = "";
//		        String result = "";
//		        while ((line = buffer.readLine()) != null) {
//		        	System.out.println("[로그:정현진] while반복문 들어옴");
//		        	
//		            result += line;
//		        }
//		        //readLine()) ==> 입력 String 값으로 리턴값 고정
//
//		        System.out.println("response body :" + result);
//
//		        // 읽었으니깐 데이터꺼내오기
//		        JsonParser parser = new JsonParser();
//		        JsonElement element = parser.parse(result); //Json element 문자열변경
//		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//		        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//		        String memberID = properties.getAsJsonObject().get("memberID").getAsString();
//		        System.out.println("[로그:정현진] memberID : "+memberID);
//		        String memberName = properties.getAsJsonObject().get("memberName").getAsString();
//		        System.out.println("[로그:정현진] memberName : "+memberName);
//		        String memberEmail = kakao_account.getAsJsonObject().get("memberEmail").getAsString();
//		        System.out.println("[로그:정현진] memberEmail : "+memberEmail);
//		        
//				//userInfo에 사용자 정보 저장
//		        userInfo.put("memberID", memberID);
//		        userInfo.put("memberName", memberName);
//		        userInfo.put("memberEmail", memberEmail);
//		        System.out.println("[로그:정현진] userInfo : "+userInfo);
//
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//
//		    MemberDTO member = memberService.findKakao(userInfo);
//		    // 저장되어있는지 확인
//		    System.out.println("findKakao(userInfo) [로그:정현진] member : "+member);
//
//		    if (member == null) {
//		        //member null 이면 정보가 저장 안되어있는거라서 정보를 저장.
//		    	memberService.insertKakao(userInfo);
//		        //저장한 member 정보 다시 가져오기 HashMap이라 형변환 시켜줌
//		    	member.setMemberID((String)userInfo.get("memberID"));
//		        member = memberService.selectOne(member);
//		        session.setAttribute("member", member);
//				
//		        //로그인 처리 후 메인 페이지로 이동
//		        view = "redirect:/";
//		        msg = "로그인 성공";
//		    } else {
//		        session.setAttribute("member", member);
//		        view = "redirect:/";
//		        msg = "로그인 성공";
//
//		    }
//		    rttr.addFlashAttribute("msg", msg);
//		    return view;
//		}
//	
//}
