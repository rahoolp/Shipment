package com.gap.logistics.inbound.shipment.infrastratucture.persistance;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentRepository;

public class InMemoryShipmentRepository implements ShipmentRepository {
	
	private Map<String, Shipment> store; 
	
	public InMemoryShipmentRepository() {
		store = new HashMap<String, Shipment>();
	}

	public Collection<Shipment> allShipments() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Shipment shipment) {
		this.store.put(shipment.getShipmentId(), shipment);
	}

	public Shipment shipmentOfId(String id) {
		Shipment shipment = this.store.get(id);
		return shipment;
	}

	public String nextIdentity() {
		
		UUID id = UUID.randomUUID();
				
		return id.toString().toUpperCase();
	}

}
