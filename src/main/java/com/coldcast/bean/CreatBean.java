package com.coldcast.bean;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreatBean {
	
    //消息队列的pub_sub模式
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("video.topic");
    }
    //消息队列的点对点模式
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("common.queue");
    }
    //自定义同时开启pub_sub和点对点模式，因为activeMQ 默认只支持一种模式
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}
