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
import com.forest.domain.PageBean;
import com.forest.service.IOrderService;
import com.forest.service.OrderServiceImp;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction extends ActionSupport{
	@Autowired
	private IOrderService os;
	
	private Integer customerId;
	
	private Integer pageNum;
	
	private Integer currentCount;
	
	private String orderId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Action(value="findOrders")
	public void findOrders() {
		//解决相应乱码
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		//分页查询
		PageBean<Order> pageBean = os.findOrderByPage(customerId,pageNum,currentCount);
		//List<Order> orders = os.findByCustomer(customerId);
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
		String json = JSONObject.toJSONString(pageBean,pFilter,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Action(value="deleteOrder",results={@Result(name="success",location="findOrders",type="redirectAction")})
	public String deleteOrder() {
		Order order = new Order();
		order.setOrderNum(orderId);
		os.deleteOrder(order);
		return SUCCESS;
	}
}
