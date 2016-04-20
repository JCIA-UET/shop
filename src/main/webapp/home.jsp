<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Shopping</title>
	<link href="style.css" rel="stylesheet"/>
	<%session.setAttribute("request_from","/home.jsp"); %>
</head>
<body>
	
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	
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
						<form action="ItemService">
							<input type="hidden" name="action" value="requestupdate">
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
						<form action="TransactionService">
							<input type="hidden" name="action" value="requestaddtocart" />
							<input type="hidden" name="idProduct" value="${item.id}" />
							<button type="submit">Add to cart</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>	
</body>
</html>