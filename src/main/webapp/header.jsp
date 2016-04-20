<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<c:choose>
	<c:when test="${not empty sessionScope.session_account}">
		<p class="welcome">Welcome, <strong>${sessionScope.session_account.username}</strong></p>
	</c:when>
	<c:otherwise>
		<p class="welcome">Please <a href="login.jsp">login</a>
	</c:otherwise>
</c:choose>

<div class="menu">
	<form action="ItemService">
		<button type="submit">Home</button>
	</form>
	<form action="ItemService" method="get">
		<input type="hidden" name="action" value="getallitems">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<button type="submit">Categories list</button>
	</form>
	<form action="revenue.jsp" class="for-staff">
		<button type="submit">Statistic</button>
	</form>	
	<form action="ItemService" method="get" class="for-staff">
		<input type="hidden" name="action" value="requestadd">
		<input type="hidden" name="itemtype" value="PRODUCT">
		<button type="submit">Add product</button>
	</form>
	<form action="ItemService" method="get" class="for-staff">
		<input type="hidden" name="action" value="requestadd">
		<input type="hidden" name="itemtype" value="CATEGORY">
		<button type="submit">Add category</button>
	</form>
	<form action="addResult.jsp" class="for-customer">
		<button type="submit">View Cart</button>
	</form>
</div>
	
<c:if test="${not empty message}">
	<h2 style="color:blue;">${message}</h2>
</c:if>