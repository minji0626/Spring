package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.spring.interceptor.LoginCheckInterceptor;
import kr.spring.interceptor.WriterCheckInterceptor;

// 자바코드 기반 설정 클래스
@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	private LoginCheckInterceptor loginCheck;
	private  WriterCheckInterceptor writerCheck;
	
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
										   .addPathPatterns("/board/delete");
		
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
				"/WEB-INF/tiles-def/board.xml"
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
}
