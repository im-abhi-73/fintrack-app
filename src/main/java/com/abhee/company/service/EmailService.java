package com.abhee.company.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	
	private final JavaMailSender mailSender;
	
	/*
	 * @Value("${spring.mail.properties.mail.smtp.from}") private String fromEmail;
	 */
	
	@SneakyThrows
	public void sendEmail(String to ,String subject , String body) {
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("hmr18717@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
	}

}
