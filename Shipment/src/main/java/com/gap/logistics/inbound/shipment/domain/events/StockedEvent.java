package com.gap.logistics.inbound.shipment.domain.events;

import java.util.Date;
import java.util.List;

import com.gap.logistics.inbound.shipment.domain.Carton;
import com.gap.logistics.inbound.shipment.domain.SKU;
import com.gap.logistics.inbound.shipment.domain.ShipmentEvent;

public class StockedEvent extends ShipmentEvent {
	
	public StockedEvent(Date dateOfEvent, String location,
			String owner, List<Carton> cartons, List<SKU> skus, int quantity, boolean microBridgeFlag, String customsEntryNo) {
		super(dateOfEvent, "ST", location, owner, cartons, skus);	
	}

	
}
