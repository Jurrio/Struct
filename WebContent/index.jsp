<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to the STRUCT!</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<ul class="header">
		<li class="active-header-menu-item"><a href="index.jsp"
			class="menu-link">Home</a></li>
		<li class="header-menu-item"><a href="addCompany"
			class="menu-link">Add Company</a></li>
		<li class="header-menu-item"><a href="ListCompanyServlet"
			class="menu-link">Dashboard</a></li>
	</ul>

	<p>Caution! If you run app on your computer - your database should
		has a user 'struct@localhost' with password 'struct'. You can change
		this parameters in constants.Database</p>

	${message}

	<img class=under-image src="img/Page-Under-Construction.png">

	<p>About:</p>
	<p>
		If you want add the company you can open page <a href="addCompany">add-company</a>
		and input name, earnings and main company (optional). When you press
		Add - new company will store in database
	</p>
	<p>
		If you want edit existing company you can open <a
			href="ListCompanyServlet">Dashboard</a> choose company and press
		"edit"
	</p>
	<p>
		For view information about company - press view on <a
			href="ListCompanyServlet">Dashboard</a>
	</p>
	<p>
		For delete company press delete on <a href="ListCompanyServlet">Dashboard</a>
	</p>

</body>
</html>