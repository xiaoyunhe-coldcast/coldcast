package com.coldcast.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.netty.NettyClient;

@RestController
public class NettyController {
	
	@Autowired
	NettyClient nettyClient;
	
	@GetMapping("netty/connect")
	public String getconnet(String ip, int port) {
		nettyClient.connect(ip, port);
		LocalDate date = LocalDate.now();
		return date.toString();
	}
	
	/**
	 *@{author} 客户端发送消息
	 *@{date} 2020年4月1日
	 *@{tags} @param ip
	 *@{tags} @param port
	 *@{tags} @param message
	 *@{tags} @return
	 */
	@GetMapping("netty/clientSend")
	public String ClientSend(String ip, int port,String message) {
		return nettyClient.sendMessage(ip,port, message);
	}
}
