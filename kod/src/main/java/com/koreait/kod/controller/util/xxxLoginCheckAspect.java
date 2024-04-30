package com.koreait.kod.controller.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Aspect
@Service
@RequiredArgsConstructor // Lombok을 사용하여 필수 생성자를 자동으로 생성 -> 의존성주입 해결 -> 멤버변수 위 @Autowired가 필요없어짐  
public class xxxLoginCheckAspect {

    private final MemberService memberService;
    

    @Around("@annotation(loginCheck)") // @LoginCheck가 붙은 메소드를 Around advice 처리
    public Object loginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck)
            throws Throwable {

    	System.out.println("[로그:정현진] loginCheck 메소드 실행");
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        String memberID = (String)session.getAttribute("memberID");
        System.out.println("[로그:정현진] memberID : "+memberID);

        String memberRole = getMemberRole(memberID);
        System.out.println("[로그:정현진] memberRole : "+memberRole);

        if (loginCheck.checkRole() == Role.ADMIN) {
            checkRole(memberRole, Role.ADMIN);
        } 
        else {
            checkRole(memberRole, Role.USER);
        }

        Object[] modifiedArgs = modifyArgsWithUserID(memberID, proceedingJoinPoint);

        return proceedingJoinPoint.proceed(modifiedArgs);
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

    private Object[] modifyArgsWithUserID(String memberID, ProceedingJoinPoint proceedingJoinPoint) {
    	System.out.println("[로그:정현진] modifyArgsWithUserID 메소드 들어옴");
        Object[] parameters = proceedingJoinPoint.getArgs();

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        for (int i = 0; i < method.getParameters().length; i++) {
            String parameterName = method.getParameters()[i].getName();
            if (parameterName.equals("userID")) {
                parameters[i] = memberID;
            }
        }
        return parameters;
    }

    private void checkRole(String role, Role requiredRole) throws CustomAccessDeniedException {
        System.out.println("[로그:정현진] checkRole 메소드 들어옴");
        if (!requiredRole.equals(Role.NOT_PERMITTED) && !requiredRole.name().equals(role)) {
            throw new CustomAccessDeniedException("권한이 없습니다. " + requiredRole.name(), role);
        }
    }

    public enum Role {
        NOT_PERMITTED, USER, ADMIN;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface LoginCheck {
        Role checkRole() default Role.USER;
    }
    
}