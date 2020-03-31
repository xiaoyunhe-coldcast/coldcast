 package com.coldcast.bean;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicSub {
	
	   @JmsListener(destination="video.topic", containerFactory="jmsListenerContainerTopic")
	   public void receive1(String text){
	      System.out.println("video.topic 消费者:receive1="+text);
	   }
	   
	   @JmsListener(destination="video.topic", containerFactory="jmsListenerContainerTopic")
	   public void receive2(String text){
	      System.out.println("video.topic 消费者:receive2="+text);
	   }
	   
	   //containerFactory="jmsListenerContainerTopic"  这里要标明自定义的jms工厂
	   @JmsListener(destination="video.topic", containerFactory="jmsListenerContainerTopic")
	   public void receive3(String text){
	      System.out.println("video.topic 消费者:receive3="+text);
	   }
}
