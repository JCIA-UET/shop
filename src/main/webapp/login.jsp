<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<form method="POST" action="LoginService">
		<table>
		 	<tr>
		 		<td><div align="right">Username</div></td>
		 		<td><input type="text" name="username">
		 	</tr>
		 	<tr>
		 		<td><div align="right">Password</div></td>
		 		<td><input type="password" name="password"/></td>
		 	</tr>
		 	<tr>
    			<td><div align="right"></div></td>
   				 <td><input name="" type="submit" value="login" /></td>
 			 </tr>
		</table>
	</form>
	
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>