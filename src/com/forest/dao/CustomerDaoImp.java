package com.forest.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.forest.domain.Customer;
import com.forest.domain.Order;

@Repository
public class CustomerDaoImp extends HibernateDaoSupport implements ICustomerDao {
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Customer> findAllCustomer() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	public void add(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	@Override
	public void del(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@Override
	public Customer findById(Integer id) {
		Customer customer = this.getHibernateTemplate().get(Customer.class, id);
		return customer;
	}

}
