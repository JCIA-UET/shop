<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
			<tr>
			  <th>Name</th>
			  <th>Quantity</th>
			</tr>
		<c:forEach var="item" items="${shopping_cart}">
			<tr>
			  <td>${item.name}</td>
			  <td>${item.quantity}</td>
			</tr>
		</c:forEach>
	</table>
	<form method="post" action="TransactionService">
		<input type="hidden" name="action" value="buy">
		<input type="submit" value="pay" >
	</form>
		
</body>
</html>