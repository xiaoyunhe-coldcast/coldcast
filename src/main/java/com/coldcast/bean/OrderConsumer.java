package com.coldcast.bean;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//　点对点的消费者
@Component
public class OrderConsumer {
	
	 @JmsListener(destination="common.queue")
	   public void receiveQueue(String text){
	      System.out.println("OrderConsumer收到的报文为OrderConsume:"+text);
	   }
}
