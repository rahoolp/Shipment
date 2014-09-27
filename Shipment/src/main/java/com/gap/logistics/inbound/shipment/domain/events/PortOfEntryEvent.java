package com.gap.logistics.inbound.shipment.domain.events;

import java.util.Date;
import java.util.List;

import com.gap.logistics.inbound.shipment.domain.Carton;
import com.gap.logistics.inbound.shipment.domain.SKU;
import com.gap.logistics.inbound.shipment.domain.ShipmentEvent;

public class PortOfEntryEvent extends ShipmentEvent {

	public PortOfEntryEvent(Date dateOfEvent, String eventCode,
			String location, String owner, List<Carton> cartons, List<SKU> skus) {
		super(dateOfEvent, "POE", location, owner, cartons, skus);
	}

}
