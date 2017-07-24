package com.forest.service;

import java.util.List;

import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.dao.ICustomerDao;
import com.forest.dao.IOderDao;
import com.forest.domain.Customer;
import com.forest.domain.Order;

@Service
public class OrderServiceImp implements IOrderService{
	@Autowired
	private ICustomerDao cd;
	
	@Autowired
	private IOderDao od;

	@Override
	public List<Order> findOrders(Customer customer) {
		
		return null;
	}

	@Override
	public List<Order> findByCustomer(Integer customerId) {
		//先根据id查询客户
		Customer customer = cd.findById(customerId);
		//再根据id查询订单
		List<Order> order = od.findByCustomer(customer);
		return order;
	}
	
}
