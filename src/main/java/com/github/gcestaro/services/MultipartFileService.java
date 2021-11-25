package com.github.gcestaro.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MultipartFileService {

	@Value("${spring.mail.username}")
	private String emailUsername;

	private final EmailService emailService;

	public void upload(MultipartFile multipartFile) {
		log.info("Sending {} by email", multipartFile.getOriginalFilename());

		emailService.sendMessageWithAttachment(emailUsername, "Teste multipartfile", "Testando",
				multipartFile);
	}
}
