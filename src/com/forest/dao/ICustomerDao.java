package com.forest.dao;

import java.util.List;

import com.forest.domain.Customer;

public interface ICustomerDao {

	public List<Customer> findAllCustomer();
	public void add(Customer customer);
	public void del(Customer customer);
	public void update(Customer customer);
	public Customer findById(Integer id);
}
