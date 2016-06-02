<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	
	<body>
		<ul class="header">
			<li class="header-menu-item"><a href="index.jsp" class="menu-link">Home</a></li>
			<li class="header-menu-item"><a href="addCompany" class="menu-link">Add Company</a></li>
			<li class="active-header-menu-item"><a href="ListCompanyServlet" class="menu-link">Dashboard</a></li>
		</ul>
		
		<form action="FindCompanyServlet" method="post">
			<input class="search-input" type="text" name="search"
					placeholder="search field"> <input class="search-button"
					type="submit" value="search">
			<p>Table:</p>
	 	</form>
	
		<table class="dash-table">
			<tr>
				<td>#</td>
				<td>Name</td>
				<td>Earnings</td>
				<td>Parent</td>
				<td>Parent.id</td>
				<td></td>
			</tr>
		<c:forEach items="${companies}" var="company">
	 		<tr>
	 			<c:set var="parent" value="${company.parent}"></c:set>
				<td>${company.id}</td>
				<td><input class="disabled" name="title" type="text" value="${company.name}" readonly></td>
				<td><input class="disabled" name="earning" type="text" value="${company.earnings}" readonly></td>
				<td><input class="disabled" name="parent" type="text" value="${parent.name}" readonly></td>
				<td><input class="disabled" name="parentId" type="text" value="${parent.id}" readonly></td>
				<td><form action="deleteCompany" method="post"> 
						<input type="text" name="id" value="${company.id}">
						<input type="submit" value="Delete">
					</form>
				</td>
				<td><form action="viewCompany" method="post">
						<input type="hidden" name="id" value="${company.id}">
						<input type="submit" value="Info">
				 	</form>
			 	</td>
			</tr>
		</c:forEach>	
		
	</table>
	<c:out value="${message}"/>
	
</body>
</html>