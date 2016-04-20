<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add to cart</title>
	<link href="style.css" rel="stylesheet"/>
	<%session.setAttribute("request_from","/addToCart.jsp"); %>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<form method="post" action="TransactionService">
		<table>
			<tr>
				<td> ID product </td>
				<td><input type="number" name="productid" value="${product.id}"></td>
			</tr>
			<tr>
				<td> Name product </td>
				<td><input type="text" name="productname" value="${product.name}"></td>
			</tr>
			
			<tr>
				<td> Quantity </td>
				<td><input type="text" name="quantity" required></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="addToCart">
		<button type="submit">Add to cart</button>
	</form>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>