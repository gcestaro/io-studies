package com.github.gcestaro.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.gcestaro.services.MultipartFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("multipart")
public class MultipartFileResource {

	private final MultipartFileService service;

	@PostMapping
	public void add(MultipartFile multipartFile) {
		log.info("File {} received", multipartFile.getOriginalFilename());

		service.upload(multipartFile);
	}
}
