package kr.spring.ch21.annot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyFirstAspect {
	/*
	 @Before
	 @AfterReturning
	 @AfterThrowing
	 @After
	 @Around
	 */
	
	@Pointcut("execution(public String launch())")
	// 주의 ! 메서드명이 getPointcut으로 정해져 있기 때문에 변경할 수 없다.
	public void getPointcut() {
		
	}
	
	//@Before("getPointcut()")
	public void before() {
		// 메서드 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! ** 메서드가 호출되기 전에 나온다!");
	}
	
	//@AfterReturning(value = "getPointcut()", returning = "msg")
	public void afterReturning(String msg) {
		// 핵심 기능이 동작된 후에 반환하는 데이터를 받아 출력하기
		// 메서드 호출이 예외를 내보내지 않고 종료했을 때 동작하는 어드바이스
		System.out.println("Hello After Returning! ** 메서드가 호출한 후 발생! 전달된 객체 : " + msg);
	}
	
//	@AfterThrowing(value = "getPointcut()", throwing="ex")
	public void afterThrowing(Exception ex) {
		// 메서드 호출이 예외를 던졌을 때 동작하는 어드바이스
		System.out.println("Hello After Throwing ** 예외가 생겼을 때 출력! 예외 : " + ex);
	}
	
//	@After("getPointcut()")
	public void after() {
		// 메서드 종료 후에 동작하는 어드바이스(예외가 발생해도 실행된다)
		System.out.println("Hello After ** 메서드 호출 된 후 출력!");
	}
	
	@Around("getPointcut()")
	public String around(ProceedingJoinPoint joinPoint) throws Throwable {
		// 메서드 호출 전 후에 동작하는 어드바이스
		// 핵심 기능 동작 전에 호출
		System.out.println("Hello Around Before! ** 메서드 호출 전 출력!");
		
		// 핵심 기능 부분
		String s = null;
		// try ~ catch ~ finally 구조로 명시해야 예외가 발생하더라도 메서드 실행 후 공통 기능 수행
		try {
			// 핵심 기능이 수행 된 후 데이터 반환
			s = (String)joinPoint.proceed();	// 핵심 기능을 갖고 있는 메서드가 실행
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 핵심 기능 이후의 동작(공통 기능)
			System.out.println("Hello Around After! ** 메서드 호출 후 출력! 반환된 객체 : " + s);
		}
		
		return s;
	}
}

