package com.coldcast.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.mqtt.ClientMQTT;
import com.coldcast.mqtt.ServerMQTT;

@RestController
public class mqttController {
	
	@Autowired
	ClientMQTT client;
	
    @Autowired
    private ServerMQTT server;
	
	
	/**
	 *@{author} 连接服务器
	 *@{date} 2020年4月1日
	 *@{tags} @param name
	 *@{tags} @param password
	 *@{tags} @return
	 */
	@GetMapping("mqtt/connect")
	public String getconnet(String name, String password) {
	    client.start(name,password,"mytopic");
		LocalDate date = LocalDate.now();
		return date.toString();
	}
	
	/**
	 *@{author} 向主题发布消息
	 *@{date} 2020年4月1日
	 *@{tags} @param topicName
	 *@{tags} @param message
	 *@{tags} @return
	 */
	@GetMapping("mqtt/clientpublish")
	public String Clientpublish(String topicName, String message) {
		client.publishMessage(topicName,message);
		LocalDate date = LocalDate.now();
		return date.toString();
	}
	
	/**
	 *@{author} 订阅主题
	 *@{date} 2020年4月1日
	 *@{tags} @param topic
	 *@{tags} @return
	 */
	@GetMapping("mqtt/subscribe")
	public String subscribe(String topic) {
		try {
			ClientMQTT client = new ClientMQTT();
			client.start("admin", "password", topic);
			client.subscribe(topic);
			return "订阅成功";
		}catch (Exception e) {
			return "订阅失败";
		}
	}
}
