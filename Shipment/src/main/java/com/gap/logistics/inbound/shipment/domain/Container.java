package com.gap.logistics.inbound.shipment.domain;

public class Container {
	private String containerId;
	
	public Container(String id) {
		containerId = id;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	
}
