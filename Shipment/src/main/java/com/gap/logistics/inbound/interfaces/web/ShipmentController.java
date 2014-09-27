package com.gap.logistics.inbound.interfaces.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gap.domain.model.DomainEvent;
import com.gap.domain.model.DomainEventSubscriber;
import com.gap.domain.model.infrastructure.DomainEventPublisher;
import com.gap.logistics.inbound.shipment.application.ShipmentService;
import com.gap.logistics.inbound.shipment.domain.SKU;
import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentDomainEvent;
import com.gap.logistics.inbound.shipment.domain.ShipmentEvent;

@Controller
public class ShipmentController {
	
	@Autowired
	ShipmentService shipmentService;
	
	@RequestMapping(value="/shipment/{shipmentId}", method = RequestMethod.GET)
	public String orderStatus(@ModelAttribute("shipment") Shipment shipment) {
		return "/shipment";
	}

	@ModelAttribute("shipment")
	private Shipment getShipment(@PathVariable("shipmentId") String shipmentId) {
		Shipment shipment = shipmentService.getShipmentOfId(shipmentId);
		return shipment;
	}
	
   @RequestMapping(value="/shipment/{shipmentId}/postEvent", method=RequestMethod.GET)
    public String postEvent(@PathVariable("shipmentId") String shipmentId, Model model) {
	   	PostEvent event = new PostEvent();
	   	event.setShipmentId(shipmentId);
        model.addAttribute("postEvent", event);
        return "postEvent";
    }
 
    @RequestMapping(value="/shipment/{shipmentId}/postEvent", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute PostEvent event, Model model) {
        Shipment shipment = shipmentService.getShipmentOfId(event.getShipmentId());
        
        List<SKU> skus = new ArrayList<SKU>();
		skus.add(new SKU("100100100", "DPO1", false, 100));
		skus.add(new SKU("100100101", "DPO1", false, 200));
		skus.add(new SKU("100100102", "DPO1", false, 300));

		ShipmentEvent shipEvent = new ShipmentEvent(new Date(), String.valueOf(event.getEventType()), event.getCarrier(),
				event.getBroker(), null, skus);
        
        shipmentService.postShipmentEvent(shipment, shipEvent);
        
        model.addAttribute("shipment", shipment);   
        
        return "shipment";
    }


}
