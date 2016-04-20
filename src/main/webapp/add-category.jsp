<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new category</title>
<link href="style.css" rel="stylesheet"/>
</head>
<body>
	<%session.setAttribute("request_from","/add-category.jsp"); %>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<form action="ItemService" method="post">
		<input type="hidden" name="action" value="add">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<label for="categoryname">Category Name</label>
		<br/>
		<input type="text" id="categoryname" name="categoryname" required>
		<br/>
		<br/>
		<button type="submit">Submit</button>
	</form>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>		
</body>
</html>