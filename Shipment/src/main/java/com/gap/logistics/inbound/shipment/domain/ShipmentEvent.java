package com.gap.logistics.inbound.shipment.domain;

import java.util.Date;
import java.util.List;

public class ShipmentEvent {
	private final Date dateOfEvent;
	private final String eventTypeCode;
	private final String location;
	private final String owner;
    private String dpoId;
    private int stockKeepingUnitId;
	
	private final List<Carton> cartons;
	private final List<SKU> skus;
	
	public ShipmentEvent(Date dateOfEvent, String eventCode, String location,
			String owner, List<Carton> cartons, List<SKU> skus) {
		super();
		this.dateOfEvent = dateOfEvent;
		this.eventTypeCode = eventCode;
		this.location = location;
		this.owner = owner;
		this.cartons = cartons;
		this.skus = skus;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public String getEventTypeCode() {
		return eventTypeCode;
	}

	public String getLocation() {
		return location;
	}

	public String getOwner() {
		return owner;
	}

	public List<Carton> getCartons() {
		return cartons;
	}

	public List<SKU> getSkus() {
		return skus;
	}
	
}
