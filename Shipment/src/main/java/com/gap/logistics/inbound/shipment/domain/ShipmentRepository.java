package com.gap.logistics.inbound.shipment.domain;

import java.util.Collection;

public interface ShipmentRepository {
		
	public Collection<Shipment> allShipments();
	public void save(Shipment shipment);
	public Shipment shipmentOfId(String id);
	public String nextIdentity();

}
