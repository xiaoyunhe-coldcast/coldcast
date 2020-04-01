package com.coldcast.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.mqtt.ClientMQTT;

@RestController
public class mqttController {
	
	@GetMapping("mqtt/connect")
	public String getconnet(String name, String password) {
		ClientMQTT client = new ClientMQTT();
	    client.start(name,password);
		LocalDate date = LocalDate.now();
		return date.toString();
	}
	
	@GetMapping("mqtt/publish")
	public String publish(String message) {
		ClientMQTT client = new ClientMQTT();
		client.publishMessage(message);
		LocalDate date = LocalDate.now();
		return date.toString();
	}
	

}
