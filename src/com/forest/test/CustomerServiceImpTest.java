package com.forest.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forest.dao.ICustomerDao;
import com.forest.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerServiceImpTest {
	@Autowired
	private ICustomerDao cd;

	@Test
	public void test() {
		Customer customer = new Customer();
		customer.setCusName("aa");
		customer.setCusPhone("13666666666");
		cd.add(customer);
	}

}
