package com.gap.domain.model.infrastructure;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.gap.domain.model.DomainEvent;
import com.gap.domain.model.DomainEventSubscriber;
import com.google.gson.Gson;


//This will publish events to JMS 
public class JMSBasedDESubscriber implements DomainEventSubscriber {
	
	private Class<?> domainEvent;

	@Autowired
	ConfigurableApplicationContext context;

  	@Autowired
  	JmsTemplate jmsTemplate;

	
	public JMSBasedDESubscriber(Class<?> domainEvent) {
		super();
		this.domainEvent = domainEvent;
	}

	public void handleEvent(DomainEvent de) {
		//if (de.getClass() == domainEvent.getClass()) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(de);
	        jmsTemplate.convertAndSend(domainEvent.getClass().getName(), jsonString);
	        System.out.println("json: " + jsonString);
//		} else {
//			System.out.println("Event type mismatch");
//		}
	}

	public Class<?> subscribedToEventType() {
		return domainEvent;
	}

}
