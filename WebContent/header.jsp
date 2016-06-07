<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	
	<body>
		
		<ul>
		
			<c:choose>
				<c:when test="${page-name == 'index'}"><li class="active-header-menu-item"> <a href="index.jsp">Home</a> </li></c:when>
				<c:otherwise><li class="header-menu-item"><a href="index.jsp">Home</a> </li></c:otherwise>
			</c:choose> 
			
			<c:choose>
				<c:when test="${page-name == 'add'}"><li class="active-header-menu-item"><a href="addCompany">Add company</a> </li></c:when>
				<c:otherwise><li class="header-menu-item"><a href="addCompany">Add company</a></li></c:otherwise>
			</c:choose>
	
			<c:choose>
				<c:when test="${page-name == 'dashboard'}"><li class="active-header-menu-item"><a href="showAll">Dashboard</a></li></c:when>
				<c:otherwise><li class="header-menu-item"><a href="showAll">Dashboard</a></li></c:otherwise>
			</c:choose>
		
		</ul>
		
	</body>
	
</html>