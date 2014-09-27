package com.gap.supplychain.order.domain;

import java.util.Collection;

public interface OrderRepository {
		
	public Collection<Order> allOrders();
	public void save(Order order);
	public Order orderOfId(String id);
	public String nextIdentity();

}
