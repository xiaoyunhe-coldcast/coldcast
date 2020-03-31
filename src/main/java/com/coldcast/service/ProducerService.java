package com.coldcast.service;

import javax.jms.Destination;

public interface ProducerService {
	
	 public void sendMessage(Destination destination, String message);
	 
	 public void publish(String msg);
	 
	 public void sendMessage(final String message);
}
