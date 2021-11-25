package com.github.gcestaro.services;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

	@Value("${spring.mail.username}")
	private String emailUsername;

	@Autowired
	private JavaMailSender emailSender;

	public void sendMessageWithAttachment(
			String to, String subject, String text, MultipartFile multipartFile) {

		try {
			log.info("creating mime message");

			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(emailUsername);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			log.info("adding attachment");
			ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(multipartFile.getBytes(),
					multipartFile.getContentType());
			helper.addAttachment("Attachment", byteArrayDataSource);

			log.info("sending email from {}", emailUsername);

			emailSender.send(message);

			log.info("done!");
		} catch (MessagingException | IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}