package com.gap.order.shipmentOrder.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.gap.domain.model.infrastructure.DomainEventPublisher;
import com.gap.domain.model.infrastructure.JMSBasedDESubscriber;
import com.gap.order.shipmentOrder.JMSDEConsumer;
import com.gap.order.shipmentOrder.domain.ShipmentDomainEvent;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gap")
@PropertySource("classpath:application.properties")
public class AppConfig {

		
    @Value("${mongo.host}")
    private String host; 

    @Value("${mongo.port}")
    private int port;

    @Value("${mongo.dbName}")
    private String dbName;

    @Value("${mongo.collectionName}")
    private String collectionName;
    
    @Value("${jms.broker}")
    private String broker;
    

//	@Bean
//	public OrderShipmentRepository shipmentRepository() throws Exception {
//		
//		OrderShipmentRepository shipmentRepository = new MongoOrderShipmentRepository(host, port, dbName, collectionName);
//		return shipmentRepository;
//		
//	}
	
	@Bean
	public ConnectionFactory jmsConnectionFactory() {
		final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(broker);
		return factory;
	}
	
	@Bean
	public Queue requestsQueue() {
		return new ActiveMQQueue("requests");
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		final JmsTemplate jmsTemplate = new JmsTemplate(jmsConnectionFactory());
		jmsTemplate.setDefaultDestination(requestsQueue());
		return jmsTemplate;
	}

    
    @Bean
    JMSDEConsumer JMSDEConsumer() {
        return new JMSDEConsumer();
    }

    @Bean
    MessageListenerAdapter messageListenerAdp(JMSDEConsumer receiver) {
        MessageListenerAdapter messageListenerAdp = new MessageListenerAdapter(receiver);
        messageListenerAdp.setDefaultListenerMethod("receiveMessage");
        return messageListenerAdp;
    }

    @Bean
    SimpleMessageListenerContainer simpleMsgLisContainer(MessageListenerAdapter messageListenerAdp,
                                             ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMsgLisContainer = new SimpleMessageListenerContainer();
        simpleMsgLisContainer.setMessageListener(messageListenerAdp);
        simpleMsgLisContainer.setConnectionFactory(connectionFactory);
        simpleMsgLisContainer.setDestinationName("java.lang.Class");
        simpleMsgLisContainer.setConcurrency("2-5");
        return simpleMsgLisContainer;
    }

}
