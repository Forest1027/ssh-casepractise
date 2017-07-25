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
		//�ȸ���id��ѯ�ͻ�
		Customer customer = cd.findById(customerId);
		//�ٸ���id��ѯ����
		List<Order> order = od.findByCustomer(customer);
		return order;
	}

	@Override
	public PageBean<Order> findOrderByPage(Integer customerId, Integer pageNum, Integer currentCount) {
		//����id��ѯ�ͻ�
		Customer customer = cd.findById(customerId);
		//��ѯ���ÿͻ����ж����ĸ���
		long totalCount = (long)od.findTotalCount(customer);
		//������ҳ��.������������ÿҳ����
		int totalPage = (int)(Math.ceil(totalCount*1.0)/currentCount);
		//����dao����з�ҳ��ѯ��ø�ҳ������Ϣ
		List<Order> orders = od.findOrderByPage(customer,pageNum,currentCount);
		//��װ����
		PageBean<Order> pageBean = new PageBean<>(pageNum, currentCount, totalCount, totalPage, orders);
		return pageBean;
	}

	@Override
	public void deleteOrder(Order order) {
		od.deletOrder(order);
	}
	
}
