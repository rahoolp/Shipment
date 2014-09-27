package com.gap.domain.model;

public interface DomainEventSubscriber {
	
	public void handleEvent(DomainEvent de);
	
	public Class<?> subscribedToEventType(); 

}
