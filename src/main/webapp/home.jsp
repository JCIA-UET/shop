<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping</title>
<link href="style.css" rel="stylesheet"/>
</head>
<body>
	<%session.setAttribute("request_from","/home.jsp"); %>
	
	<c:choose>
		<c:when test="${not empty sessionScope.session_account}">
			<p class="welcome">Welcome, <strong>${sessionScope.session_account.username}</strong></p>
		</c:when>
		<c:otherwise>
			<p class="welcome">Please <a href="login.jsp">login</a>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${not empty message}">
		<h2 style="color:blue;">${message}</h2>
	</c:if>
	
	<c:if test="${not empty items}">
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Category</th>
				<th>Description</th>
				<th>Operation</th>
			</tr>
			<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.categoryId}</td>
					<td>${item.description}</td>
					<td>
						<form action="ItemService" method="post">
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="itemtype" value="PRODUCT">
							<input type="hidden" name="itemid" value="${item.id}">
							<button type="submit">Update</button>
						</form>
						<form action="ItemService" method="post">
							<input type="hidden" name="action" value="remove">
							<input type="hidden" name="itemtype" value="PRODUCT">
							<input type="hidden" name="itemid" value="${item.id}">
							<button type="submit">Remove</button>
						</form>
						<form action="TransactionService" method="post">
							<input type="hidden" name="action" value="add" />
							<input type="hidden" name="idProduct" value="${item.id}" />
							<input type="submit" value="ADD">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
	
	<br/>
	<form action="ItemService" method="get">
		<input type="hidden" name="action" value="getallitems">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<button type="submit">See categories list</button>
	</form>
	<br/>
	<form action="ItemService" method="post">
		<input type="hidden" name="action" value="add">
		<input type="hidden" name="itemtype" value="PRODUCT">
		<button type="submit">Add more products</button>
	</form>
	
</body>
</html>