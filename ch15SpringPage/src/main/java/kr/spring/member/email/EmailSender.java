package kr.spring.member.email;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailSender {
	@Autowired
	protected JavaMailSender mailSender;
	
	public void sendEmail (Email email) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			msg.setFrom();
			msg.setSubject(email.getSubject()); // 제목을 명시
			msg.setText(email.getContent());	//  내용을 명시
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));	//받는 사람
		} catch (MessagingException e) {
			// 예외 발생의 경우
			log.error(e.toString()); //로그에 찍어 확인하기
		}
		
		mailSender.send(msg);	// 메세지 발송하기
	}
}
