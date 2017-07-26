<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.bg {
	max-width: 630px;
	padding: 15px;
	margin: 0 auto;
}
</style>
<script type="text/javascript">
//删除客户
function delCustomer(customerId) {
	location.href="${pageContext.request.contextPath}/customer/delCustomer?id="+customerId;
}
//分页相关变量
var pageNum=1;//当前页码
var currentCount=5;//每页个数
var totalCount = 0;//总条数
var totalPage = 0//总页数

//定义cid的全局变量
var cid;
//查询订单
function findOrders(customerId) {
	cid=customerId;

	$.post("${pageContext.request.contextPath}/order/findOrders",{"customerId":customerId,"pageNum":pageNum,"currentCount":currentCount},function(data) {
		$("#orderInfo").html("");
		var pageBean = eval(data);
		var content = pageBean.content;
		$.each(content,function(i) {
			var content1="<tr><td>"+content[i].orderNum+"</td><td>"+content[i].price+"</td><td>"+content[i].receiverInfo
			+"</td><td>"+content[i].customer.cusName+"</td><td><a href='#' onclick=\"deleteOrder('"+content[i].orderNum+"')\">DELETE</a>&nbsp&nbsp</td></tr>";
			$("#orderInfo").append(content1);
		});
		//接收数据
		pageNum = pageBean.pageNum;
		currentCount = pageBean.currentCount;
		totalCount = pageBean.totalCount;
		totalPage = pageBean.totalPage;
		//分页拼接
		//清空page
		$("#page").html("");
		$("#page").append("<ul class='pagination' id='pagUl'></ul>");
		//判断是否能向上翻页
		if (pageNum==1) {
			$("#pagUl").append("<li class='disabled'><a href='#' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");
		}else {
			$("#pagUl").append("<li class='active'><a href='#' aria-label='Previous' onclick='prePage()'><span aria-hidden='true'>&laquo;</span></a></li>");
		}
		//判断中间数字
		for (var i = 1; i <=totalPage; i++) {
			if (i==pageNum) {
				$("#pagUl").append("<li class='active'><a href='#' onclick='select("+i+")'>"+i+"</a></li>");
			} else {
				$("#pagUl").append("<li><a href='#' onclick='select("+i+")'>"+i+"</a></li>");
			}
		}
		//判断能否能向下翻页
		if (pageNum==totalPage) {
			$("#pagUl").append("<li class='disabled'><a href='#' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");
		}else {
			$("#pagUl").append("<li class='active'><a href='#' aria-label='Next' onclick='nextPage()'> <span aria-hidden='true'>&raquo;</span></a></li>");
		}
	},"json");
}

function prePage() {
	pageNum = pageNum-1;
	findOrders(cid);
}

function nextPage() {
	pageNum = pageNum+1;
	findOrders(cid);
}

function select(i) {
	pageNum=i;
	findOrders(cid);
}
//删除订单
function deleteOrder(orderId) {
	alert(orderId);
	$.post("${pageContext.request.contextPath}/order/deleteOrder",{"orderId":orderId},function(data) {
		findOrders(cid);
	});
}
</script>
</head>
<body>
	<table class="table table-hover bg">
		<tr>
			<td colspan="4"><a
				href="${pageContext.request.contextPath}/addUser.jsp"
				class="btn btn-primary btn-sm active" role="button">ADD CUSTOMER</a>
			</td>
		</tr>
		<tr>
			<td>ID</td>
			<td>CUSTOMER</td>
			<td>CUSTOMER NAME</td>
			<td>PHONE NUMBER</td>
			<td>DELETE/QUERY</td>
		</tr>
		<s:iterator value="customers" var="c" status="vs">
			<tr>
				<td><s:property value="#vs.index+1" /></td>
				<td><img alt="not accessible"
					src="<s:property value='#c.cusImgsrc'/>" class="img-rounded" /></td>
				<td><s:property value="#c.cusName" /></td>
				<td><s:property value="#c.cusPhone" /></td>
				<td><a href="#"
					onclick="delCustomer(<s:property value='#c.id'/>)">DELETE</a>&nbsp&nbsp&nbsp
					<a href="#" data-toggle="modal" data-target="#myModal"
					onclick="findOrders(<s:property value='#c.id'/>)">QUERY</a></td>
			</tr>
		</s:iterator>
	</table>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<table class="table table-hover bg" id="orderInfo">
						<tr>
							<td>ORDER NUMBER</td>
							<td>PRICE</td>
							<td>RECEIVER</td>
							<td>CUSTOMER NAME</td>
							<td>ADD/DELETE</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<nav id="page">
				
					</nav>
				</div>
			</div>
		</div>
	</div>
</body>
</html>