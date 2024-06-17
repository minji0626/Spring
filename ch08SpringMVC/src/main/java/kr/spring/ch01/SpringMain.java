package kr.spring.ch01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		/*
		 * applicationContext.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		 * GenericXmlApplicationContext의 생성자에 설정파일 정보를 전달할 때는  "applicationContext.xml"
		 * 또는 String[] configLocations = new String[]{"applicationContext.xml"} 형태로 여러개의 설정정보를 한 번에 지정할 수 있음.
		 */
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//JVM 종료될 때 ApplicationContext를 종료하는 작업을 수행하도록 등록
		context.registerShutdownHook();

		//객체를 컨테이너로부터 읽어들임 
		MessageBean messageBean = (MessageBean)context.getBean("messageBean");
		messageBean.sayHello("장영실");

		context.close(); //어플리케이션 종료시 컨테이너에 존재하는 모든 빈(객체)를 종료
	}
}
