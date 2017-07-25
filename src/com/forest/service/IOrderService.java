package com.forest.service;

import java.util.List;

import com.forest.domain.Order;
import com.forest.domain.PageBean;

public interface IOrderService {

	public List<Order> findByCustomer(Integer customerId);

	public PageBean<Order> findOrderByPage(Integer customerId, Integer pageNum, Integer currentCount);

	public void deleteOrder(Order order);
}
