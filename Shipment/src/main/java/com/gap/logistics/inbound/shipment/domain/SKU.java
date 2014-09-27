package com.gap.logistics.inbound.shipment.domain;

public class SKU {

	private String id;
	private String dpoId;
	private boolean prepack;
	private long quantity;
	
	public SKU(String id, String dpoId, boolean prepack, long quantity) {
		super();
		this.id = id;
		this.dpoId = dpoId;
		this.prepack = prepack;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public String getDpoId() {
		return dpoId;
	}

	public boolean isPrepack() {
		return prepack;
	}

	public long getQuantity() {
		return quantity;
	}
	
}
