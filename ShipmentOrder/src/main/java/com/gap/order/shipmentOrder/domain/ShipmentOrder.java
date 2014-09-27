package com.gap.order.shipmentOrder.domain;

import java.util.ArrayList;

import com.gap.domain.model.infrastructure.DomainEventPublisher;

public class ShipmentOrder {

	private String orderId;
	
	public void processEvent(ShipmentDomainEvent shipmentDE) {
        System.out.println("Event Received:" + shipmentDE.getEventType() + " " + shipmentDE.getShipmentId());
	}
	
}
