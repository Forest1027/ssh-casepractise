package com.forest.service;

import java.util.List;

import com.forest.domain.Customer;
import com.forest.domain.Order;

public interface IOrderService {
	public List<Order> findOrders(Customer customer);

	public List<Order> findByCustomer(Integer customerId);
}
