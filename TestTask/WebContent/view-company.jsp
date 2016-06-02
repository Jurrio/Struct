<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>About ${company.name}</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
		
	<body>
		<ul class="header">
			<li class="active-header-menu-item"><a href="index.jsp" class="menu-link">Home</a></li>
			<li class="header-menu-item"><a href="addCompany" class="menu-link">Add Company</a></li>
			<li class="header-menu-item"><a href="ListCompanyServlet" class="menu-link">Dashboard</a></li>
		</ul>
		
		${message}
		<h1>HELLO!</h1>
		Parent company: ${company.parent.name}
		<form action="viewCompany" method="post">
			<input name="id" type="hidden" value="${company.parent.id}">
			<input type="submit" value="info">
		</form> 
		<p>Child companies: </p>
		<c:forEach items="${companies}" var="child">
			${child.name}
			<form action="viewCompany" method="post">
				<input name="id" type="hidden" value="${child.id}">
				<input type="submit" value="info">
			</form> 
		</c:forEach>		

	</body>
</html>