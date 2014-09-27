package com.gap.logistics.inbound.shipment.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gap.domain.model.infrastructure.DomainEventPublisher;


public class Shipment {
    private String shipmentId;
    private String carrier;
    private String broker;
    
    private List<ShipmentEvent> shipmentEvents;
    private List<Container> containers;

    public Shipment(String shipmentId) {
        this.shipmentId = shipmentId;
    }

	public String getShipmentId() {
		return shipmentId;
	}
    
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public List<ShipmentEvent> getShipmentEvents() {
		return shipmentEvents;
	}

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}
	
	public void processEvent(ShipmentEvent event) {
		if (shipmentEvents == null) {
			shipmentEvents = new ArrayList<ShipmentEvent>();
		}
		this.shipmentEvents.add(event);
		
		ShipmentDomainEvent shipmentDE = new ShipmentDomainEvent(event.getDateOfEvent(), shipmentId,
				event.getEventTypeCode());
		DomainEventPublisher.publish(shipmentDE);
	}

	public void addContainer(Container c) {
		if (containers == null) {
			containers = new ArrayList<Container>();
		}
		this.containers.add(c);
	}

}
