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
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.forest.domain.Customer;
import com.forest.domain.Order;
import com.forest.service.IOrderService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport{
	@Autowired
	private IOrderService os;
	
	private Integer customerId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@Action(value="findOrders")
	public void findOrders() {
		//解决相应乱码
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		List<Order> orders = os.findByCustomer(customerId);
		System.out.println(customerId+"--------id");
		PropertyFilter pFilter = new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if ("cusPhone".equals(arg1)) {
					return false;
				}
				if ("id".equals(arg1)) {
					return false;
				}
				if ("orders".equals(arg1)) {
					return false;
				}
				return true;
			}
		};
		String json = JSONObject.toJSONString(orders,pFilter,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
