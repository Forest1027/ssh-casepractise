package com.forest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forest.dao.ICustomerDao;
import com.forest.domain.Customer;

@Service
@Transactional
public class CustomerServiceImp implements ICustomerService{
	@Autowired
	private ICustomerDao cd;
	
	@Override
	public List<Customer> findAllCustomer() {
		List<Customer> customers =cd.findAllCustomer();
		return customers;
	}
	
	@Override
	public void add(Customer customer) {
		cd.add(customer);
	}

	@Override
	public void del(Customer customer) {
		int id = customer.getId();
		customer = cd.findById(id);
		cd.del(customer);
	}

	@Override
	public void update(Customer customer) {
		cd.update(customer);
	}

	@Override
	public Customer findById(Integer id) {
		return cd.findById(id);
	}

}
