<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update category</title>
<link href="style.css" rel="stylesheet"/>
</head>
<body>
	<%session.setAttribute("request_from","/update-category.jsp"); %>
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
	
	<form action="ItemService" method="post">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<input type="hidden" id="itemid" name="itemid" value="${item.id}">
		
		<label for="categoryid">Category ID</label>
		<br/>
		<input type="text" id="categoryid" name="categoryid" value="${item.id}">
		<br/>
		<label for="categoryname">Category Name</label>
		<br/>
		<input type="text" id="categoryname" name="categoryname" value="${item.name}" required>
		<br/>
		<button type="submit">Submit</button>
	</form>
	<br/>
	<form action="ItemService" method="get">
		<input type="hidden" name="action" value="getallitems">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<button type="submit">See categories list</button>
	</form>
</body>
</html>