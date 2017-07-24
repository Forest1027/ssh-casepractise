package com.forest.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.forest.domain.Customer;
import com.forest.service.ICustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerAction extends ActionSupport {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Autowired
	private ICustomerService cs;

	@Action(value = "findAllCustomer", results = { @Result(name = "success", location = "/customerList.jsp") })
	public String findAllCustomer() {
		List<Customer> customers = cs.findAllCustomer();
		// 回写页面
		ActionContext.getContext().getValueStack().set("customers", customers);
		return SUCCESS;
	}

	@Action(value = "delCustomer", results = {
			@Result(name = "success", location = "findAllCustomer", type = "redirectAction") })
	public String delCustomer() {
		//根据id删除
		Customer customer = new Customer();
		customer.setId(id);
		cs.del(customer);
		return SUCCESS;
	}
}
