package com.gap.domain.model.infrastructure;

import java.util.ArrayList;
import java.util.List;

import com.gap.domain.model.DomainEvent;
import com.gap.domain.model.DomainEventSubscriber;

public class DomainEventPublisher {
	
	private static final List<DomainEventSubscriber> registeredSubscribers = new ArrayList<DomainEventSubscriber>();
	
	public static void publish(final DomainEvent aDomainEvent) {
		Class <?> eventType = aDomainEvent.getClass(); 
		for (DomainEventSubscriber subscriber : registeredSubscribers) { 
			Class <?> subscribedTo = subscriber.subscribedToEventType(); 
			if (subscribedTo == eventType || subscribedTo == DomainEvent.class) { 
				subscriber.handleEvent( aDomainEvent); 
			} 
		} 
	}
	
	public static void subscribe(DomainEventSubscriber subscriber) {
		registeredSubscribers.add(subscriber);		
	}
}
