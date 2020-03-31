package com.coldcast.service;

import javax.jms.Destination;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService{
	
	 @Autowired
	   private JmsMessagingTemplate jmsTemplate; //用来发送消息到broker的对象
	   
	   //发送消息，destination是发送到的队列，message是待发送的消息
	   @Override
	   public void sendMessage(Destination destination, String message) {
	      System.out.println("OrderConsumer发送的报文为:123");
	      jmsTemplate.convertAndSend(destination, message);
	   }
	   
	   //发送消息，destination是发送到的队列，message是待发送的消息
	   @Override
	   public void sendMessage(final String message) {
	      jmsTemplate.convertAndSend( message);
	   }
	   
	   @Autowired
	   private Topic topic;
	   
	    @Override
	   public void publish(String msg) {
	       System.out.println("pubsub 报文发送成功");
	       this.jmsTemplate.convertAndSend(this.topic, msg);
	   }
}
