package com.coldcast.mqtt;

import java.util.Optional;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import com.coldcast.util.logUtil;

/**
 * Title : 客户端
 * @{author} Administrator
 * @{date} 2020年4月1日
 * @{description} 
 */
@Component
public class ClientMQTT {
	
    public static final String HOST = "tcp://localhost:61613";
    private static final String clientid = "client11";
    private MqttClient client;
    private MqttConnectOptions options;

    public MqttClient start(String userName, String passWord, String topicName) {
        try {
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调
            client.setCallback(new PushCallback());
            MqttTopic topic = client.getTopic(topicName);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            options.setWill(topic, "close".getBytes(), 2, true);
            client.connect(options);
            //订阅消息
            int[] Qos  = {1};
            String[] topic1 = {topicName};
            client.subscribe(topic1, Qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return client;
    }
    
    /**
     *@{author} 发布消息
     *@{date} 2020年4月1日
     *@{tags} @param message
     */
    public void  publishMessage(String topicName, String message) {
    	//查询是否有该主题
    	MqttTopic topic = client.getTopic(topicName);
    	Optional<MqttTopic> optional = Optional.ofNullable(topic);
    	if(optional.isPresent()) {
    		start("admin","password",topicName);
    	}
        logUtil.info(message);
        MqttMessage msg = new MqttMessage();
        msg.setQos(1);
        msg.setPayload(message.getBytes());
        try {
			client.publish(topicName, msg);
		} catch (MqttPersistenceException e1) {
			e1.printStackTrace();
		} catch (MqttException e1) {
			e1.printStackTrace();
		}
//    	ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//    	scheduledExecutorService.scheduleWithFixedDelay(()->{
//    	},2, 10, TimeUnit.SECONDS);
    }
    
//    /**
//     *@{author} 订阅新主题
//     *@{date} 2020年4月1日
//     *@{tags} @return
//     */
//    public String subscribe (String topic) {
//    	MqttClient client = start("admin","password",topic);
//    	return null;
//    }
    
}

