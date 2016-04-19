<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="js/calRevenue.js"></script>
	<link href="style.css" rel="stylesheet"/>s
	<title>Insert title here</title>
</head>
<body>
	<c:if test="${isLogin == true}">
		<p>You are not login. Please <a href="/shop/login.jsp">login</a> first</p>
	</c:if>
	<form action="StatistService" method="post">
		<div>
			<select id="revenueType" name="action" onchange="setType()" size="1">
				<option value=" ">Choose action</option>
				<option value="get-day-revenue">Get Daily Revenue</option>
				<option value="get-month-revenue">Get Month Revenue</option>
				<option value="get-total-revenue">Get Total Revenue</option>
				<option value="get-top-selling">Top 10 Best Selling Products</option>
			</select>
		</div>
		<div>
			<select id="month" name="month" onchange="setDay()" size="1">
				<option value=" ">Choose a month</option>
				<option value="1">January</option>
			    <option value="2">February</option>
			    <option value="3">March</option>
			    <option value="4">April</option>
			    <option value="5">May</option>
			    <option value="6">June</option>
			    <option value="7">July</option>
			    <option value="8">August</option>
			    <option value="9">September</option>
			    <option value="10">October</option>
			    <option value="11">November</option>
			    <option value="12">December</option>
			</select>
		</div>
		<div>
			<select id="day" name="day" onchange="" size="1">
				<option value=" ">Choose a day</option>
				<option value="1">1</option>
			    <option value="2">2</option>
			    <option value="3">3</option>
			    <option value="4">4</option>
			    <option value="5">5</option>
			    <option value="6">6</option>
			    <option value="7">7</option>
			    <option value="8">8</option>
			    <option value="9">9</option>
			    <option value="10">10</option>
			    <option value="11">11</option>
			    <option value="12">12</option>
			    <option value="13">13</option>
			    <option value="14">14</option>
			    <option value="15">15</option>
			    <option value="16">16</option>
			    <option value="17">17</option>
			    <option value="18">18</option>
			    <option value="19">19</option>
			    <option value="20">20</option>
			    <option value="21">21</option>
			    <option value="22">22</option>
			    <option value="23">23</option>
			    <option value="24">24</option>
			    <option value="25">25</option>
			    <option value="26">26</option>
			    <option value="27">27</option>
			    <option value="28">28</option>
			    <option value="29">29</option>
			    <option value="30">30</option>
			    <option value="31">31</option>
			</select>
		</div>
		<div>
			<select id="year" name="year" onchange="" size="1">
				<option value="0">Choose a year</option>
				<option value="2016">2016</option>
			</select>
		</div>
		<button>Submit</button>
	</form>
	<c:if test="${ action == 'get-top-selling' }">
		<table>
			<tr>
				<th>Category ID</th>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Number of product sold</th>
			</tr>
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.categoryId}</td>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.quantity}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${action != null}">
		<c:if test="${action != 'get-top-selling'}">
			<table border="1">
				<tr>
					<th>Number of orders</th>
					<th>Number of products sold</th>
					<th>Revenue</th>
				</tr>
				<tr>
					<td>${result[0].intValue()}</td>
					<td>${result[1].intValue()}</td>
					<td>${result[2].intValue()}</td>
				</tr>
			</table>
		</c:if>
	</c:if>
</body>
</html>