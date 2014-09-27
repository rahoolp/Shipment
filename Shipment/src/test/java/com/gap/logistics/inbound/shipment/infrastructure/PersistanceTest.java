package com.gap.logistics.inbound.shipment.infrastructure;

import junit.framework.TestCase;

import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentRepository;
import com.gap.logistics.inbound.shipment.infrastratucture.persistance.MongoShipmentRepository;

public class PersistanceTest extends TestCase {
	
	private ShipmentRepository repo = null;
	
	protected void setUp() throws Exception {
		//this.repo = new InMemoryShipmentRepository();
//		this.repo = new MongoShipmentRepository(fName, 0, fName, fName);
		super.setUp();
	}
	
	protected void teardown() throws Exception {
		
	}
	
	public void testSave() throws Exception {
		
		String id = repo.nextIdentity();
		
		Shipment shipment = new Shipment(id);
		repo.save(shipment);
		
		Shipment shipment1 = repo.shipmentOfId(id);
		
		assertEquals(shipment1.getShipmentId(), shipment.getShipmentId());
		
	}
}
