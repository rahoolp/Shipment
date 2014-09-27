package com.gap.logistics.inbound.shipment.domain.events;

import java.util.Date;
import java.util.List;

import com.gap.logistics.inbound.shipment.domain.Carton;
import com.gap.logistics.inbound.shipment.domain.SKU;
import com.gap.logistics.inbound.shipment.domain.ShipmentEvent;

public class LoadPortEvent extends ShipmentEvent {
	private int unitQuantity;
	private boolean microBridgeIndicator;
	
	public LoadPortEvent(Date dateOfEvent, String location,
			String owner, List<Carton> cartons, List<SKU> skus, int quantity, boolean microBridgeFlag) {
		super(dateOfEvent, "LP", location, owner, cartons, skus);
		unitQuantity = quantity;
		microBridgeIndicator = microBridgeFlag;
		
	}

	public int getUnitQuantity() {
		return unitQuantity;
	}

	public boolean isMicroBridgeIndicator() {
		return microBridgeIndicator;
	}

}
