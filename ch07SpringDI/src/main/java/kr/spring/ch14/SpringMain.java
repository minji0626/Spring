package kr.spring.ch14;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringMain {
	public static void main(String[] args) {
		// applicationcontext2.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
		//JVM 종료될 때 ApplicationContext를 종료하는 작업을 수행하도록 등록
		context.registerShutdownHook();

		/*
		 * 빈 객체의 타입이나 이름을 이용하여 의존 관계를 자동으로 설정할 수 있는 기능
		 * bean 태그의 autowire 속성에 지정할 수 있는 값 
		 * byName: 프로퍼티의 이름과 같은 이름을 갖는 빈 객체를 설정 
		 * byType : 프로퍼티의 타입과 같은 타입을 갖는 빈 객체를 설정
		 * constructor : 생성자 파라미터 타입과 같은 타입을 갖는 빈 객체를 생성자에 전달
		 */
		
		WriteArticleService writeArticleService = (WriteArticleService)context.getBean("writeArticleService");
		writeArticleService.write();
		
		context.close(); //어플리케이션 종료시 컨테이너에 존재하는 모든 빈(객체)를 종료
	}
}