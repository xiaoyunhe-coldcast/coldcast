package com.coldcast.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.netty.NettyClient;

@RestController
public class NettyColler {
	
	@GetMapping("netty/connect")
	public String getconnet(String ip, int port) {
		NettyClient nettyClient = new NettyClient();
		nettyClient.connect(ip, port);
		LocalDate date = LocalDate.now();
		return date.toString();
	}
	

}
