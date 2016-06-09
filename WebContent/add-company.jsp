<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<li class="active-header-menu-item"><a href="addCompany" class="menu-link">Add Company</a></li>
			<li class="header-menu-item"><a href="ListCompanyServlet" class="menu-link">Dashboard</a></li>
		</ul>
		
		<div>
			<div><p>Please, add the company.</p></div>
			<form action="addCompany" method="post">
				<fieldset class="add-form">	
					<div class="field">
						<label for="name">Name</label>
						<input type="text" id="company" name="company" placeholder="write name of your company" required>
					</div>
				
					<div class="field">
						<label for="earnings">Earnings</label>
						<input type="text" id="earnings" name="earnings" placeholder="100K" required>
					</div>
					
					<div class="field">
						<label for="parent">ParentCompany</label>
						<select id="parent" name="parent">
							<option value="0">No company</option>
							<c:forEach items="${companies}" var="company">
								<option value="${company.id}">${company.id} ${company.name} </option>
							</c:forEach>
						</select>
					</div>
									
					<button type="submit">Add company!</button>     
      			</fieldset>
			</form>
		</div>
	<p>Message:<c:out value="${message}"/></p>

	</body>
</html>