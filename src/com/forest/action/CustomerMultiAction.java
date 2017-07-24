package com.forest.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.forest.domain.Customer;
import com.forest.service.ICustomerService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@Namespace("/customer")
@ParentPackage("struts-default")
public class CustomerMultiAction extends ActionSupport {
	@Autowired
	private ICustomerService cs;

	private File cusImg;
	private String cusImgFileName;
	private String cusName;
	private String cusPhone;

	public File getCusImg() {
		return cusImg;
	}

	public void setCusImg(File cusImg) {
		this.cusImg = cusImg;
	}

	public String getCusImgFileName() {
		return cusImgFileName;
	}

	public void setCusImgFileName(String cusImgFileName) {
		this.cusImgFileName = cusImgFileName;
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

	@Action(value = "addCustomer", results = {
			@Result(name = "success", location = "findAllCustomer", type = "redirectAction"),
			@Result(name = "input", location = "/error.jsp") })
	public String addCustomer() {
		// 文件上传
		// 指定文件上传路径
		if (cusImg == null || "".equals(cusImg) || cusImgFileName == null || "".equals(cusImgFileName)) {

		} else {
			File destFile = new File(ServletActionContext.getServletContext().getRealPath("/upload"), cusImgFileName);
			// 保存文件
			try {
				FileUtils.copyFile(cusImg, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 用户名添加
		Customer customer = new Customer();
		customer.setCusName(cusName);
		customer.setCusPhone(cusPhone);
		String cusImgsrc = ServletActionContext.getRequest().getContextPath() + "/upload/" + cusImgFileName;
		customer.setCusImgsrc(cusImgsrc);
		cs.add(customer);
		return SUCCESS;
	}
}
