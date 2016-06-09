<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit ${company.name}</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<ul class="header">
		<li class="header-menu-item"><a href="index.jsp"
			class="menu-link">Home</a></li>
		<li class="header-menu-item"><a href="addCompany"
			class="menu-link">Add Company</a></li>
		<li class="header-menu-item"><a href="ListCompanyServlet"
			class="menu-link">Dashboard</a></li>
	</ul>

	<div>
		<p>ChangeParent</p>
		<form action="UpdateCompanyServlet" method="post">
			<div class="field">
				<label for="parent">ParentCompany</label> <select id="parent"
					name="parent">
					<option value="0" selected="selected">No company</option>
					<c:forEach items="${companies}" var="nextCompany">
						<option value="${nextCompany.id}">${nextCompany.id}
							${nextCompany.name}</option>
					</c:forEach>
				</select>

				<p>
					<label for="name">Company's name</label> <input type="text"
						id="company" name="company" value="${company.name}">
				</p>

				<p>
					<label for="earnings">Earnings</label> <input type="text"
						id="earnings" name="earnings" value="${company.earnings}">
				</p>

				<input type="hidden" id="id" name="id" value="${company.id}">
				<input type="submit" value="Edit!">
			</div>
		</form>
	</div>
</body>
</html>