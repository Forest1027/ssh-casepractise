package com.forest.service;

import java.util.List;

import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.dao.ICustomerDao;
import com.forest.dao.IOderDao;
import com.forest.domain.Customer;
import com.forest.domain.Order;
import com.forest.domain.PageBean;

@Service
public class OrderServiceImp implements IOrderService{
	@Autowired
	private ICustomerDao cd;
	
	@Autowired
	private IOderDao od;

	

	@Override
	public List<Order> findByCustomer(Integer customerId) {
		//先根据id查询客户
		Customer customer = cd.findById(customerId);
		//再根据id查询订单
		List<Order> order = od.findByCustomer(customer);
		return order;
	}

	@Override
	public PageBean<Order> findOrderByPage(Integer customerId, Integer pageNum, Integer currentCount) {
		//根据id查询客户
		Customer customer = cd.findById(customerId);
		//查询到该客户所有订单的个数
		long totalCount = (long)od.findTotalCount(customer);
		//计算总页数.订单总数除以每页个数
		int totalPage = (int)(Math.ceil(totalCount*1.0)/currentCount);
		//调用dao层进行分页查询获得该页订单信息
		List<Order> orders = od.findOrderByPage(customer,pageNum,currentCount);
		//封装数据
		PageBean<Order> pageBean = new PageBean<>(pageNum, currentCount, totalCount, totalPage, orders);
		return pageBean;
	}

	@Override
	public void deleteOrder(Order order) {
		od.deletOrder(order);
	}
	
}
