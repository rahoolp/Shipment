package com.gap.logistics.inbound.interfaces.web;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gap.logistics.inbound.shipment.application.ShipmentService;
import com.gap.logistics.inbound.shipment.domain.Shipment;
import com.gap.logistics.inbound.shipment.domain.ShipmentRepository;

@Controller
@RequestMapping("/shipments")
public class ShipmentRestController {
	
	@Autowired
	ShipmentService shipmentService;
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public ResponseEntity<Void> createShipment() throws Exception {
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value="/{shipmentId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Shipment> shipment(@PathVariable String shipmentId) throws Exception {
		Shipment shipment = shipmentService.getShipmentOfId(shipmentId);
		
		ResponseEntity<Shipment> shipmentResponse = new ResponseEntity<Shipment>(shipment, HttpStatus.OK);
		
		return shipmentResponse;
	}
		
}
