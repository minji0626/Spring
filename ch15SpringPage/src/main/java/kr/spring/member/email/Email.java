package kr.spring.member.email;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class Email {
	private String subject;
	private String content;
	private String receiver;
}
