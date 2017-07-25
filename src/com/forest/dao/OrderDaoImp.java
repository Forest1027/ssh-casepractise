package com.forest.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	
	@Override
	public long findTotalCount(Customer customer) {
		//查询该用户订单总数
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.eq("customer", customer));
		criteria.setProjection(Projections.rowCount());
		long totalCount = (long)this.getHibernateTemplate().findByCriteria(criteria).get(0);
		return totalCount;
	}

	@Override
	public List<Order> findOrderByPage(Customer customer, Integer pageNum, Integer currentCount) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		criteria.add(Restrictions.eq("customer", customer));
		return (List<Order>)this.getHibernateTemplate().findByCriteria(criteria, (pageNum-1)*currentCount, currentCount);
	}

	@Override
	public void deletOrder(Order order) {
		this.getHibernateTemplate().delete(order);
	}

}
