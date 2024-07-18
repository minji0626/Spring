package kr.spring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.spring.interceptor.AutoLoginCheckInterceptor;
import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;
import kr.spring.websocket.SocketHandler;

// 자바코드 기반 설정 클래스
@Configuration
@EnableWebSocket
public class AppConfig implements WebMvcConfigurer, WebSocketConfigurer{
	
	private AutoLoginCheckInterceptor autoLoginCheck;
	private LoginCheckInterceptor loginCheck;
	private WriterCheckInterceptor writerCheck;
	
	@Bean
	public AutoLoginCheckInterceptor interceptor() {
		autoLoginCheck = new AutoLoginCheckInterceptor();
		
		return autoLoginCheck;
	}
	
	@Bean
	public LoginCheckInterceptor interceptor2() {
		loginCheck = new LoginCheckInterceptor();
		return loginCheck;
	}
	
	@Bean
	public WriterCheckInterceptor interceptor4() {
		writerCheck = new WriterCheckInterceptor();
		
		return writerCheck;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(autoLoginCheck).addPathPatterns("/**")
											   .excludePathPatterns("/images/**")
											   .excludePathPatterns("/image_upload/**")
											   .excludePathPatterns("/upload/**")
											   .excludePathPatterns("/css/**")
											   .excludePathPatterns("/js/**")
											   .excludePathPatterns("/member/login")
											   .excludePathPatterns("/member/logout");		
		
		// 인터셉터 등록하기
		// login check 하는 인터셉터 등록 
		// .addPathPatterns > 이걸로 다양한 페이지들을 등록하여 인터셉터를 등록해주는 것
		// 회원제 서비스마다 등록해준다고 생각하면 될 듯
		// 너무 파일이 많아진다면 *표시로도 추가가 가능함 지금 당장은 쓰이는 페이지가 얼마 없기 때문에 하나 하나 다 등록해준다
		registry.addInterceptor(loginCheck).addPathPatterns("/member/myPage")
										   .addPathPatterns("/member/update")
										   .addPathPatterns("/member/changePassword")
										   .addPathPatterns("/member/delete")
										   .addPathPatterns("/board/write")
										   .addPathPatterns("/board/update")
										   .addPathPatterns("/board/delete")
										   .addPathPatterns("/talk/talkRoomWrite")
										   .addPathPatterns("/talk/talkList")
										   .addPathPatterns("/talk/talkDetail");
		
		// Writer Check Interceptor 설정하기
		registry.addInterceptor(writerCheck).addPathPatterns("/board/update")
											.addPathPatterns("/board/delete");
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		
		// XML 설정 파일 경로 지정 -> 배열이기 때문에 다양한 파일 지정 가능함
		configurer.setDefinitions(new String[] {
				"/WEB-INF/tiles-def/main.xml",
				"/WEB-INF/tiles-def/member.xml",
				"/WEB-INF/tiles-def/board.xml",
				"/WEB-INF/tiles-def/talk.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		
		return tilesViewResolver;
	}
	
	// 웹소켓 세팅하기
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketHandler(), "message-ws").setAllowedOrigins("*");
	}
	
	
	@Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
    	Properties prop = new Properties();
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.transport.protocol", "smtp");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.debug", "true");
    	
    	JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
    	javaMail.setHost("smtp.gmail.com");
    	javaMail.setPort(587);
    	javaMail.setDefaultEncoding("utf-8");
    	javaMail.setUsername("movie.cineverse@gmail.com");
    	javaMail.setPassword("aqmjslsdwaxrliaf");
    	javaMail.setJavaMailProperties(prop);
    	return javaMail;
    }
}
