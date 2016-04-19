<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="TransactionService">
		<table>
			<tr>
				<td> ID product </td>
				<td><input type="text" value="${product.id}"  name ="idProduct" disabled></td>
			</tr>
			<tr>
				<td> Name product </td>
				<td><input type="text" value="${product.name}" disabled></td>
			</tr>
			
			<tr>
				<td> Quantity </td>
				<td><input type="text" name="quantity"></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="addToCart">
		<input type="submit" value="ADD TO CART">
	
	</form>
</body>
</html>