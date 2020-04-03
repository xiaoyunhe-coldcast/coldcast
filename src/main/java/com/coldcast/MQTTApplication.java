package com.coldcast;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.coldcast.mqtt.ServerMQTT;

@ComponentScan(basePackages = "com.coldcast.*")
@SpringBootApplication
public class MQTTApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MQTTApplication.class, args);
    }

    @Autowired
    private ServerMQTT server;

    @Override
    public void run(String... args) {
    	 server.message = new MqttMessage();
         server.message.setQos(1);
         server.message.setRetained(true);
//         ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//         scheduledExecutorService.scheduleWithFixedDelay(() ->{
        	 try {
        		 LocalDateTime nowDate = LocalDateTime.now();
                 server.message.setPayload(nowDate.toString().getBytes());
        		 server.publish(server.topic11 , server.message);
			} catch (MqttPersistenceException e) {
				e.printStackTrace();
			} catch (MqttException e) {
				e.printStackTrace();
			}
//         }, 2, 10, TimeUnit.SECONDS);
         System.out.println(server.message.isRetained() + "------ratained状态");
    }
}

