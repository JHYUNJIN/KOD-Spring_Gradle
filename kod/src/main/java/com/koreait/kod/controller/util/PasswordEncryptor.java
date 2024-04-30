package com.koreait.kod.controller.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptor {
	public String encrypt(String memberPW) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); // 256비트 해시 값을 생성
			messageDigest.update(memberPW.getBytes()); // 비밀번호(문자열)를 바이트 배열로 변환
			byte byteData[] = messageDigest.digest(); // 계산된 해시 값을 바이트 배열에 저장
//			StringBuilder stringBuilder = new StringBuilder(); // 다중 스레드 환경일 경우 StringBuffer 추천
//			for (int i = 0; i < byteData.length; i++) { // 비트연산을 수행하여 바이트를 양수로 변환 후 16진수로 표현
//				stringBuilder.append(Integer.toString((byteData[i] & 0xff)+0x100,16).substring(1));
//			}
			StringBuilder encryptedPassword = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) { // 바이트를 양수로 변환 후 16진수 문자열로 변환
				String hex = Integer.toHexString(0xff & byteData[i]); // 16진수로 변환
				if(hex.length()==1) { // 만약 16진수가 1자리 수 일 경우 앞에 0을 추가하여 2자리수로 만듦
					encryptedPassword.append('0'); // 10 => 0A , 15 => 0F -> 암호화 결과의 일관성 유지
				}
				// 생성된 16진수 문자열을 StringBuilder 객체에 추가
				encryptedPassword.append(hex);
			}
			return encryptedPassword.toString(); // 생성된 (암호화)비밀번호 반환
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("[로그:정현진] 비밀번호 암호화 실패");
			throw new RuntimeException(); // 비밀번호 생성 실패시 예외처리
		} // try ~ catch
	} // encrypt
} // class

/*
 * 16진수를 나타내는 경우에는 보통 두 자리수를 사용합니다. 
 * 하지만 일부 경우에는 16진수 표기법에서 한 자리 숫자로 표현될 수 있습니다. 
 * 예를 들어, 0부터 9까지의 숫자는 동일하게 표현될 수 있지만, 10부터 15까지의 숫자는 각각 a부터 f까지의 문자로 표현됩니다. 
 * 따라서, 16진수로 표현할 때 한 자리 수인 경우에는 '0'부터 '9'까지의 숫자 사이에 'a'부터 'f'까지의 문자를 포함시켜야 합니다.

그러므로, 만약 변환된 문자열이 한 자리 수인 경우에는 앞에 '0'을 붙여주어야 합니다. 이렇게 함으로써 모든 16진수 문자열이 두 자리로 표현되게 됩니다
 */



/*
회원가입시 비밀번호를 암호화하기 위해 SHA-256을 사용하는 것은 좋은 선택입니다. 
이때 StringBuilder와 StringBuffer 중에서는 StringBuilder를 더 추천합니다.

동기화의 필요성: StringBuffer는 스레드 간에 안전한 동기화된 연산을 지원합니다. 
이것은 멀티스레드 환경에서 유용할 수 있지만, 대부분의 경우에는 단일 스레드로 작업하기 때문에 
이러한 동기화는 불필요한 오버헤드를 초래할 수 있습니다. 
StringBuilder는 단일 스레드 환경에서 사용할 때 동기화를 제공하지 않으므로 성능이 더 좋습니다.

성능: StringBuilder는 단일 스레드 환경에서의 성능이 더 좋습니다. 
StringBuffer는 모든 메서드가 synchronized 키워드로 동기화되어 있기 때문에 추가적인 동기화 오버헤드가 있습니다. 
반면에 StringBuilder는 동기화를 하지 않으므로 더 빠릅니다.
*/