package kr.spring.ch20.pojo;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyFirstAdvice {
	// 공통 기능 수행
	/*
	 구현 가능한 Advice(언제 공통 기능을 핵심 기능에 적용할지를 정의) 종류
	 종류						설명
	 Before Advice				대상 객체의 메서드 호출 전에 공통 기능을 실행
	 After Returning Advice		대상 객체의 메서드가 예외 없이 실행한 이후에 공통 기능을 실행
	 After Throwing Advice		대상 객체의 메서드를 실행하는 도중 예외가 발생한 경우에 공통 기능을 실행
	 After Advice				대상 객체의 메서드를 실행하는 도중 예외가 발생했는지의 여부와 상관 없이 메서드
	 							실행 후 공통 기능을 실행 (try-catch-finally의 finally 블럭과 유사함)
	 Around Advice				대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행함
	 */
	
	public void before() {
		// 메서드 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! ** 메서드가 호출되기 전에 나온다!");
	}
	
	public void afterReturning(String msg) {
		// 핵심 기능이 동작된 후에 반환하는 데이터를 받아 출력하기
		// 메서드 호출이 예외를 내보내지 않고 종료했을 때 동작하는 어드바이스
		System.out.println("Hello After Returning! ** 메서드가 호출한 후 발생! 전달된 객체 : " + msg);
	}
	
	public void afterThrowing(Exception ex) {
		// 메서드 호출이 예외를 던졌을 때 동작하는 어드바이스
		System.out.println("Hello After Throwing ** 예외가 생겼을 때 출력! 예외 : " + ex);
	}
	
	public void after() {
		// 메서드 종료 후에 동작하는 어드바이스(예외가 발생해도 실행된다)
		System.out.println("Hello After ** 메서드 호출 된 후 출력!");
	}
	
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
