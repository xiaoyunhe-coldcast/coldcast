package com.coldcast.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherController {
	
	@GetMapping("/")
	public String getconnet() {
		LocalDateTime date = LocalDateTime.now();
		return date.toString();
	}

}
