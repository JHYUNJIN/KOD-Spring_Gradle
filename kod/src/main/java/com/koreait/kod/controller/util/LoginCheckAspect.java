package com.koreait.kod.controller.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Aspect
@Service
@RequiredArgsConstructor // Lombok을 사용하여 필수 생성자를 자동으로 생성 -> 의존성주입 해결 -> 멤버변수 위 @Autowired가 필요없어짐  
public class LoginCheckAspect {

    private final MemberService memberService;

    @Around("@annotation(loginCheck)") // @LoginCheck가 붙은 메소드를 Around advice 처리
    public Object loginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck)
            throws Throwable {

    	System.out.println("[로그:정현진] loginCheck 메소드 실행");
    	
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();

        if (loginCheck.checkRole() == Role.ADMIN) { // @LoginCheck(checkRole = Role.ADMIN)인 경우
            checkRole(getMemberRole((String)session.getAttribute("memberID")), Role.ADMIN); // ADMIN 권한 검사
        } 
        else { // @LoginCheck(checkRole = Role.ADMIN)이 아닌경우 => USER
            checkRole(getMemberRole((String)session.getAttribute("memberID")), Role.USER); // USER 권한 검사
        }

        return proceedingJoinPoint.proceed();
    }
    
    private String getMemberRole(String memberID) {
    	System.out.println("[로그:정현진] getMemberRole 메소드 들어옴");
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setSearchCondition("checkRole");
        memberDTO.setMemberID(memberID);
        System.out.println("[로그:정현진] memberID : " + memberID);
        String role = memberService.selectOne(memberDTO).getMemberRole();
        System.out.println("[로그:정현진] role : "+role);
        return role;
    }

    private void checkRole(String role, Role requiredRole) throws CustomAccessDeniedException {
        System.out.println("[로그:정현진] checkRole 메소드 들어옴");
        if (!(requiredRole.equals(Role.NOT_PERMITTED) || requiredRole.name().equals(role))) { // 권한이 없거나 role이 다르다면
        	// 권한이 없을 경우 CustomAccessDeniedException 생성자를 생성하여 예외처리
            throw new CustomAccessDeniedException("권한이 없습니다. " + requiredRole.name(), role); 
        }
    }

    public enum Role {
        NOT_PERMITTED, USER, ADMIN; // 권한없음, 일반유저(고객), 관리자
    }

    // LoginCheck 어노테이션(annotation) 정의
    @Retention(RetentionPolicy.RUNTIME) // @LoginCheck의 정보를 런타임동안 유지시켜 줌
    @Target(ElementType.METHOD) // 메소드에 적용할 수 있도록 선언
    public @interface LoginCheck { // @interface 선언 할 때는 일반적으로 메소드의 리턴 타입을 명시해야 함
        Role checkRole() default Role.USER; // 리턴타입을 Role타입으로 지정, Role 타입에는 NOT_PERMITTED, USER, ADMIN 으로 열거함
    }
}

/* 참고) Role의 리턴타입을 열거형인 enum으로 정의한 이유
명확한 유형 정의: 열거형을 사용하면 코드의 가독성이 향상됩니다. Role이라는 사용자 역할은 일반적으로 제한된 몇 가지 값(예: USER, ADMIN)으로 구성됩니다. 열거형을 사용하면 이러한 값들을 명확하게 정의할 수 있으며, 다른 곳에서 사용할 때 코드를 더 이해하기 쉽게 만듭니다.

안전한 타입 지정: 열거형을 사용하면 컴파일러가 타입 안전성을 보장합니다. 즉, 잘못된 유형의 값을 사용하려고 하면 컴파일 시에 오류가 발생합니다. 이는 실수로 잘못된 문자열이나 숫자를 사용하여 역할을 설정하는 것을 방지합니다.

제한된 유형 지정: 열거형을 사용하면 허용된 역할 값의 범위를 명확하게 제한할 수 있습니다. 예를 들어, USER와 ADMIN 이외의 역할을 사용하려고 하면 컴파일러가 경고를 표시하거나 오류를 발생시킵니다.

간결하고 확장 가능한 코드: 열거형을 사용하면 코드를 더 간결하게 작성할 수 있습니다. 또한 향후에 새로운 역할이 추가되더라도 열거형을 확장하여 쉽게 추가할 수 있습니다.

따라서 열거형은 "특히 제한된 유형의 값으로 작업할 때 유용"하며, "역할과 같이 고정된 값을 표현할 때 사용하기 적합"합니다. 이러한 이유로 열거형을 사용하여 Role을 정의하는 것이 바람직합니다.
 */


