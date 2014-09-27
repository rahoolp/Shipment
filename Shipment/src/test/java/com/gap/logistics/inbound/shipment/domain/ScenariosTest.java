package com.gap.logistics.inbound.shipment.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.ConnectionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gap.domain.model.DomainEvent;
import com.gap.domain.model.DomainEventSubscriber;
import com.gap.domain.model.infrastructure.DomainEventPublisher;
import com.gap.logistics.inbound.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfig.class)
@ComponentScan(basePackages = "com.gap")
public class ScenariosTest {
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	@Autowired
    ApplicationContext ctx;

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ConnectionFactory jmsConnectionFactory;	
	
	@Test
	public void testXF() {
		
		String id = shipmentRepository.nextIdentity();
		
		Shipment shipment = new Shipment(id);
		shipment.setBroker("Kerry");
		shipment.setCarrier("Fedex");
		
		Container container1 = new Container("C1");
		Container container2 = new Container("C2");
		
		shipment.addContainer(container1);
		shipment.addContainer(container2);

		List<Carton> cartons = new ArrayList<Carton>();
		List<SKU> skus = new ArrayList<SKU>();
		skus.add(new SKU("100100100", "DPO1", false, 100));
		skus.add(new SKU("100100101", "DPO1", false, 200));
		skus.add(new SKU("100100102", "DPO1", false, 300));

		ShipmentEvent event = new ShipmentEvent(new Date(), "XF", "CHN",
				"Arvind Mills", null, skus);
		
		shipment.processEvent(event);
		
		shipmentRepository.save(shipment);
		
		Shipment shipment1 = shipmentRepository.shipmentOfId(id);
		
		assertEquals(shipment1.getShipmentId(), shipment.getShipmentId());
		
	}
	
	@Test
	public void testAddLPToExistingShipment() {
		String id = "53caf76ba0ee55311d2211ce";
		
		Shipment shipment = shipmentRepository.shipmentOfId(id);
		
		List<SKU> skus = new ArrayList<SKU>();
		skus.add(new SKU("100100100", "DPO1", false, 100));
		skus.add(new SKU("100100101", "DPO1", false, 200));
		skus.add(new SKU("100100102", "DPO1", false, 300));

		ShipmentEvent event = new ShipmentEvent(new Date(), "LP", "CHN",
				"Arvind Mills", null, skus);
		class ShipmentEventSubscriber implements DomainEventSubscriber {
			
			ShipmentDomainEvent domainEvent;

			public void handleEvent(DomainEvent aDomainEvent) {
				domainEvent = (ShipmentDomainEvent) aDomainEvent;
				System.out.print("got an event:" + aDomainEvent.getDateOfEvent());
			}

			public Class subscribedToEventType() {
				return ShipmentDomainEvent.class;
			}

			
		}
		
		DomainEventPublisher.subscribe(new ShipmentEventSubscriber());
		
		shipment.processEvent(event);
		
		shipmentRepository.save(shipment);
		
		Shipment shipment1 = shipmentRepository.shipmentOfId(id);
		
//		assertTrue(shipment1.getShipmentEvents().contains(event));
		
	}

}
