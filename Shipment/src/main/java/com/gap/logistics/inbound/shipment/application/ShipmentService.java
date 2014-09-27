package com.gap.logistics.inbound.shipment.application;

import org.springframework.beans.factory.annotation.Autowired;

import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentEvent;
import com.gap.logistics.inbound.shipment.domain.ShipmentRepository;

public class ShipmentService {
	private final ShipmentRepository shipmentRepository;
	
	@Autowired
	public ShipmentService(ShipmentRepository shipmentRepository) {
		super();
		this.shipmentRepository = shipmentRepository;
	}
	
	public Shipment getShipmentOfId(String shipmentId) {
		Shipment shipment = shipmentRepository.shipmentOfId(shipmentId);
		return shipment;
	}
	
	public void postShipmentEvent(Shipment shipment, ShipmentEvent event) {
		shipment.processEvent(event);
		shipmentRepository.save(shipment);
	}

}
