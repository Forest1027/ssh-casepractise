package com.forest.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="t_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cusName;
	private String cusPhone;
	private String cusImgsrc;
	@OneToMany(mappedBy="customer",targetEntity=com.forest.domain.Order.class)
	@Cascade(CascadeType.ALL)
	private Set<Order> orders = new HashSet<>();
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer id, String cusName, String cusPhone, String cusImgsrc, Set<Order> orders) {
		super();
		this.id = id;
		this.cusName = cusName;
		this.cusPhone = cusPhone;
		this.cusImgsrc = cusImgsrc;
		this.orders = orders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusImgsrc() {
		return cusImgsrc;
	}

	public void setCusImgsrc(String cusImgsrc) {
		this.cusImgsrc = cusImgsrc;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", cusName=" + cusName + ", cusPhone=" + cusPhone + ", cusImgsrc=" + cusImgsrc
				+ ", orders=" + orders + "]";
	}

}
