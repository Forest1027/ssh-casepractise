package com.forest.dao;

import java.util.List;

import com.forest.domain.Customer;
import com.forest.domain.Order;

public interface IOderDao {
	public List<Order> findOrders(Customer customer);

	public List<Order> findByCustomer(Customer customer);
	
	public long findTotalCount(Customer customer);

	public List<Order> findOrderByPage(Customer customer, Integer pageNum, Integer currentCount);

	public void deletOrder(Order order);
}
