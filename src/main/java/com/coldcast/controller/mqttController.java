package com.coldcast.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coldcast.mqtt.ClientMQTT;
import com.coldcast.mqtt.ServerMQTT;

/**
 * Title : mqtt服务
 * @{author} Administrator
 * @{date} 2020年4月2日
 * @{description} 
 */
@RestController
public class mqttController {
	
	@Autowired
	ClientMQTT client;
	
	/**
	 *@{author} 连接服务器
	 *@{date} 2020年4月1日
	 *@{tags} @param name
	 *@{tags} @param password
	 *@{tags} @return
	 */
	@GetMapping("mqtt/connect")
	public String getconnet(String name, String password,String topic) {
	    client.start(name,password,topic);
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
		client.start("admin","password",topicName);
		client.publishMessage(topicName,message);
		LocalDate date = LocalDate.now();
		return date.toString();
	}
}
