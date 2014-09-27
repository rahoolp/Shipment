package com.gap.order.shipmentOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.gap.domain.model.infrastructure.DomainEventPublisher;
import com.gap.order.shipmentOrder.domain.ShipmentDomainEvent;
import com.gap.order.shipmentOrder.infrastructure.GSONSerializer;

public class JMSDEConsumer {
    @Autowired
    ConfigurableApplicationContext context;

    public void receiveMessage(String message) {
    	ShipmentDomainEvent shipmentDE = GSONSerializer.deserialize(message);	
    	DomainEventPublisher.publish(shipmentDE);
        System.out.println("Message Received:" + shipmentDE.getEventType() + " " + shipmentDE.getShipmentId() );
        
        RestTemplate restTemplate = new RestTemplate();
        Order order = restTemplate.getForObject("http://graph.facebook.com/pivotalsoftware", Order.class);
        System.out.println("Name:    " + page.getName());
    }
}
