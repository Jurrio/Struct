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
			<li class="active-header-menu-item"><a href="index.jsp" class="menu-link">Home</a></li>
			<li class="header-menu-item"><a href="addCompany" class="menu-link">Add Company</a></li>
			<li class="header-menu-item"><a href="ListCompanyServlet" class="menu-link">Dashboard</a></li>
		</ul>
		
		<p>Your database should has a user 'struct@localhost' with password 'struct'</p>
		
		<form action="checkDB" method="post">
			<input type="submit" value="check database!">
		</form>
		${message}

		
	</body>
</html>