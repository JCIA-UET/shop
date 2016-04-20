<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header">
	<div class="wrap">
		<ul>
			<li class="menu">
				<form action="ItemService" method="get" onclick="submit()">
					<span>Home</span>
				</form>
			</li>
			<li class="menu">
				<form id="category-list" action="ItemService" method="get" onclick="submit()">
					<input type="hidden" name="action" value="getallitems">
					<input type="hidden" name="itemtype" value="CATEGORY">
					<span>Category List</span>
				</form>
			</li>
			<li class="menu">
				<form id="statistic" action="revenue.jsp" class="for-staff" onclick="submit()">
					<span>Statistic</span>
				</form>
			</li>
			<li class="menu">
				<form id="add-product" action="ItemService" method="get" class="for-staff" onclick="submit()">
					<input type="hidden" name="action" value="requestadd">
					<input type="hidden" name="itemtype" value="PRODUCT">
					<span>Add Product</span>
				</form>
			</li>
			<li class="menu">
				<form id="add-category" action="ItemService" method="get" class="for-staff" onclick="submit()">
					<input type="hidden" name="action" value="requestadd">
					<input type="hidden" name="itemtype" value="CATEGORY">
					<span>Add Category</span>
				</form>
			</li>
			<li class="menu">
				<form action="addResult.jsp" class="for-customer" onclick="submit()">
					<span>View Cart</span>
				</form>
			</li>
			<li class="menu login">
			<c:choose>
				<c:when test="${not empty sessionScope.session_account}">
					Welcome <strong>${sessionScope.session_account.username}</strong>
				</c:when>
				<c:otherwise>
					<a href="login.jsp">Login</a>
				</c:otherwise>
			</c:choose>
			</li>
		</ul>
	</div>
</div>

<!-- <div class="menu">
	<form action="ItemService">
		<button type="submit">Home</button>
	</form>
	<form action="ItemService" method="get">
		<input type="hidden" name="action" value="getallitems">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<button type="submit">Categories list</button>
	</form>
	<form action="revenue.jsp" class="for-staff">
		<button type="submit">Statistic</button>
	</form>	
	<form action="ItemService" method="get" class="for-staff">
		<input type="hidden" name="action" value="requestadd">
		<input type="hidden" name="itemtype" value="PRODUCT">
		<button type="submit">Add product</button>
	</form>
	<form action="ItemService" method="get" class="for-staff">
		<input type="hidden" name="action" value="requestadd">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<button type="submit">Add category</button>
	</form>
	<form action="addResult.jsp" class="for-customer">
		<button type="submit">View Cart</button>
	</form>
</div> -->
	
<c:if test="${not empty message}">
	<h2 style="color:blue;">${message}</h2>
</c:if>