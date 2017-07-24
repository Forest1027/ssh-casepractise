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
//查询订单
function findOrders(customerId) {
	$.post("${pageContext.request.contextPath}/order/findOrders",{"customerId":customerId},function(data) {
		alert(data)
	},"json");
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
				<td><img alt="not accessible" src="<s:property value='#c.cusImgsrc'/>" class="img-rounded"/></td>
				<td><s:property value="#c.cusName" /></td>
				<td><s:property value="#c.cusPhone" /></td>
				<td><a href="#" onclick="delCustomer(<s:property value='#c.id'/>)">DELETE</a>&nbsp&nbsp&nbsp
				<a href="#" data-toggle="modal" data-target="#myModal" onclick="findOrders(<s:property value='#c.id'/>)">QUERY</a></td>
			</tr>
		</s:iterator>
	</table>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>