package com.gap.order.shipmentOrder.domain;

import java.util.Date;

import com.gap.domain.model.DomainEvent;

public class ShipmentDomainEvent implements DomainEvent {
	
	private Date dateOfEvent;
	private String shipmentId;
	private String eventType;

	
	public ShipmentDomainEvent(Date dateOfEvent, String shipmentId,
			String eventType) {
		super();
		this.dateOfEvent = dateOfEvent;
		this.shipmentId = shipmentId;
		this.eventType = eventType;
	}



	public String getShipmentId() {
		return shipmentId;
	}



	public String getEventType() {
		return eventType;
	}



	public Date getDateOfEvent() {
		return dateOfEvent;
	}

}
