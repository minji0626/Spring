package kr.spring.ch12;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringMain {
	public static void main(String[] args) {
		// applicationcontext2.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		//JVM 종료될 때 ApplicationContext를 종료하는 작업을 수행하도록 등록
		context.registerShutdownHook();

		BookClient bookClient = (BookClient)context.getBean("bookClient");
		System.out.println(bookClient);
		
		context.close(); //어플리케이션 종료시 컨테이너에 존재하는 모든 빈(객체)를 종료
	}
}