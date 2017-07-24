package com.forest.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.forest.domain.Customer;
import com.forest.domain.Order;

@Repository
public class OrderDaoImp extends HibernateDaoSupport implements IOderDao {
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Order> findOrders(Customer customer) {
		return null;
	}

	@Override
	public List<Order> findByCustomer(Customer customer) {
		List<Order> order = (List<Order>) this.getHibernateTemplate().find("from Order o where o.customer=?", customer);
		return order;
	}

}
