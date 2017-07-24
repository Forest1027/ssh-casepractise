package com.forest.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_order")
public class Order {
	@Id
	@GenericGenerator(name="myuuid",strategy="uuid")
	@GeneratedValue(generator="myuuid")
	private String orderNum;//id
	
	private String receiverInfo;
	@Column(precision=23,scale=2)
	private BigDecimal price;
	
	@ManyToOne(targetEntity=com.forest.domain.Customer.class)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;//Íâ¼ü
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String orderNum, String receiverInfo, BigDecimal price, Customer customer) {
		super();
		this.orderNum = orderNum;
		this.receiverInfo = receiverInfo;
		this.price = price;
		this.customer = customer;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getReceiverInfo() {
		return receiverInfo;
	}
	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [orderNum=" + orderNum + ", receiverInfo=" + receiverInfo + ", price=" + price + ", customer="
				+ customer + "]";
	}
	
}
