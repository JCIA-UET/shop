<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new product</title>
</head>
<body>
	<%session.setAttribute("request_from","/add-product.jsp"); %>
	<c:if test="${not empty message}">
		<h2 style="color:blue;">${message}</h2>
	</c:if>

	<form action="ItemService" method="post">
		<input type="hidden" name="action" value="add">
		<input type="hidden" name="itemtype" value="PRODUCT">
		<label for="productname">Product Name</label>
		<input type="text" id="productname" name="productname" required>
		<br/>
		<label for="productprice">Price</label>
		<input type="number" step="0.01" id="productprice" name="productprice" required>
		<br/>
		<label for="quantity">Quantity</label>
		<input type="number" id="quantity" name="quantity" required>
		<br/>
		<label for="categoryid">Category ID</label>
		<input type="text" id="categoryid" name="categoryid" required>
		<br/>
		<label for="description">Description</label>
		<input type="text" id="description" name="description">
		<br/>
		<button type="submit">Submit</button>
	</form>
</body>
</html>