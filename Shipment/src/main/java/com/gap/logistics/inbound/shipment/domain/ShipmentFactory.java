package com.gap.logistics.inbound.shipment.domain;

public interface ShipmentFactory {
	Shipment createShipment();

	Shipment createShipmentWithoutContainer();

}
