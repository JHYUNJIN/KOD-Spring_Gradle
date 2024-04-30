package com.koreait.kod.controller.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MindDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindDiaryApplication.class, args);
	}
}


/* [정현진]
자동 프록시 생성 방식을 사용하기 위해서는 @EnableAspectJAutoProxy 어노테이션을 사용해야 합니다.
(사실 @SpringBootApplication 안에있는 @EnableAutoConfiguration에 AOP 설정이 되어있다고 합니다. 따라서, @EnableAspectJAutoProxy 생략이 가능하나 공부를 위해 설정했습니다.)

또한, proxyTargetClass를 true로 설정해 타깃 클래스를 상속받아 프록시를 구현하도록 강제해야 합니다.

false로 설정을 하면 프록시 객체를 생성할 때 인터페이스를 구현한 방식으로 프록시를 생성합니다. 이 경우, 실제 타깃 클래스를 프록시 객체로 감싸버려서 만약 타깃 클래스를 다른 곳에서 DI해야 할 경우 타깃 빈을 찾지 못하기 때문입니다.


프록시란, 자신이 클라이언트가 사용하려고 하는 실제 대상 타겟 객체인 것처럼 위장해서 클라이언트의 요청을 받아주는 대리자/대리인의 역할을 하는 객체를 얘기한다.
프록시로 요청을 받고 프록시가 요청을 타깃 실제 객체에 요청을 위임한다. 프록시를 사용해서 부가기능을 타깃에게 부여할 수 있다.

AOP
⇒ aspect : one part of a situation, problem, subject
⇒ aspect : 어떤 기능의 한 부분
애스팩트란 애플리케이션의 핵심을 담고 있지는 않지만, 애플리케이션을 구성하는 한 요소이고, 핵심기능에 부가적인 의미를 갖는 모듈을 가르킨다.

AOP는 부가기능 코드인 어드바이스와, 어디에 부가기능을 적용할지 결정하는 포인트컷을 갖고 있다.

이렇게 핵심기능과 부가 기능을 분리함으로써 개발하는 방법은 AOP라고 부른다. 핵심 기능은 순수하게 핵심만을 담은 코드로만 존재하게 된다.

스프링에서 AOP는 다이내믹 프록시 방식을 사용해서 프록시 객체를 생성한다.
System.out.println("[로그:정현진]";
*/