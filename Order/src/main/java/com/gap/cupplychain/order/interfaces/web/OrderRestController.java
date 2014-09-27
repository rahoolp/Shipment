package com.gap.cupplychain.order.interfaces.web;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.gap.supplychain.order.application.OrderService;
import com.gap.supplychain.order.domain.Order;

@Controller
@RequestMapping("/orders")
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET, value="/{orderId}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Order> order(@PathVariable String orderId) throws Exception {
		Order order = orderService.getorderOfId(orderId);
		
		ResponseEntity<Order> orderResponse = new ResponseEntity<Order>(order, HttpStatus.OK);
		
		return orderResponse;
	}
		
	@RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<Order> getAllOrders() {		
		return orderService.getAllOrders();
    }

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, UriComponentsBuilder builder) {
        orderService.createOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/{id}")
                        .buildAndExpand(order.getOrderId()).toUri());

        return new ResponseEntity<Order>(order, headers, HttpStatus.CREATED);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//    public ResponseEntity<Order> dropOrder(@PathVariable String id) {
//
//        OrderDeletedEvent orderDeleted = orderService.deleteOrder(new DeleteOrderEvent(UUID.fromString(id)));
//
//        if (!orderDeleted.isEntityFound()) {
//            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
//        }
//
//        Order order = Order.fromOrderDetails(orderDeleted.getDetails());
//
//        if (orderDeleted.isDeletionCompleted()) {
//            return new ResponseEntity<Order>(order, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<Order>(order, HttpStatus.FORBIDDEN);
//    }
	
}
