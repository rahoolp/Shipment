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

import com.gap.logistics.inbound.shipment.application.ShipmentService;
import com.gap.logistics.inbound.shipment.domain.SKU;
import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentEvent;

@Controller
public class PostEventController {
	
	@Autowired
	ShipmentService shipmentService;
	

    @RequestMapping(value="/postEvent", method=RequestMethod.GET)
    public String postEvent(@ModelAttribute PostEvent event, Model model) {
        model.addAttribute("postEvent", new PostEvent());
        return "postEvent";
    }
 
    @RequestMapping(value="/postEvent", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute PostEvent event, Model model) {
        Shipment shipment = shipmentService.getShipmentOfId(event.getShipmentId());
        
        List<SKU> skus = new ArrayList<SKU>();
		skus.add(new SKU("100100100", "DPO1", false, 100));
		skus.add(new SKU("100100101", "DPO1", false, 200));
		skus.add(new SKU("100100102", "DPO1", false, 300));

		ShipmentEvent shipEvent = new ShipmentEvent(new Date(), String.valueOf(event.getEventType()), event.getCarrier(),
				event.getBroker(), null, skus);
        
        model.addAttribute("shipment", shipment);        
        return "shipment";
    }
}
