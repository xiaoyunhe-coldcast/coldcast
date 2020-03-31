package com.coldcast.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.service.ProducerService;

@RestController
public class OrderController {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private Queue queue;

	@GetMapping("order")
	public Object order(String msg) {
		producerService.sendMessage(queue, msg);
		return null;
	}

	@GetMapping("pabsub")
	public Object pabsub(String msg) {
		producerService.publish(msg);
		return null;
	}
}
