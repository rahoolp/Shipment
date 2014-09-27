package com.gap.supplychain.order.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.gap.supplychain.order.domain.Order;
import com.gap.supplychain.order.domain.OrderRepository;

public class OrderService {
	private final OrderRepository orderRepository;
	
	@Autowired
	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	public Order getorderOfId(String orderId) {
		Order order = orderRepository.orderOfId(orderId);
		return order;
	}

	public Collection<Order> getAllOrders() {
		Collection<Order> orders = orderRepository.allOrders();
		return orders;
	}

	public void createOrder(Order order) {
		orderRepository.save(order);
	}

}
