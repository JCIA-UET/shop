<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update product</title>
<link href="style.css" rel="stylesheet"/>
</head>
<body>
	<%session.setAttribute("request_from","/update-product.jsp"); %>
	<c:if test="${not empty message}">
		<h2 style="color:blue;">${message}</h2>
	</c:if>
	
	<c:choose>
		<c:when test="${not empty sessionScope.session_account}">
			<p class="welcome">Welcome, <strong>${sessionScope.session_account.username}</strong></p>
		</c:when>
		<c:otherwise>
			<p class="welcome">Please <a href="login.jsp">login</a>
		</c:otherwise>
	</c:choose>
	
	<form action="ItemService" method="post" id="update-product">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="itemtype" value="PRODUCT">
		<input type="hidden" id="itemid" name="itemid" value="${item.id}">
		
		<label for="productid">Product ID</label>
		<br/>
		<input type="text" id="productid" name="productid" value="${item.id}">
		<br/>
		<label for="productname">Product Name</label>
		<br/>
		<input type="text" id="productname" name="productname" value="${item.name}" required>
		<br/>
		<label for="productprice">Price</label>
		<br/>
		<input type="number" step="0.01" id="productprice" name="productprice" value="${item.price}" required>
		<br/>
		<label for="quantity">Quantity</label>
		<br/>
		<input type="number" id="quantity" name="quantity" value="${item.quantity}" required>
		<br/>
		<label for="categoryid">Category</label>
		<br/>
		<select name="categoryid">
		  <c:forEach var="category" items="${categories}">
		  	<option value="${category.id}">${category.name}</option>
		  </c:forEach>
		</select>
		<br/>
		<label for="description">Description</label>
		<br/>
		<textarea name="description" id="description" form="update-product">${item.description}</textarea>
		<br/>
		<br/>
		<button type="submit">Submit</button>
	</form>
	<br/>
	<form action="ItemService">
		<button type="submit">See products list</button>
	</form>	
</body>
</html>