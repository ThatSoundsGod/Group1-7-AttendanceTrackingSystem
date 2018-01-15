<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- //[START imports]--%>
<%@ page import="com.ase2017.ats.Tutorial" %>
<%@ page import="com.ase2017.ats.Student" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
<title>Registration</title>
<link rel="shortcut icon" type="image/x-icon; charset=binary" href="TUM_Web_Logo_blau.ico" />
 
</head>

<body>
	<h1>Attendance Tracking System</h1>
	
	<h2>Registration</h2>

	<form action="/RegistrationServlet">
		First name:<br> 
		<input type="text" name="firstname"><br> 
		Last name:<br> 
		<input type="text" name="lastname" > <br>
		Student ID:<br> 
		<input type="text" name="id" > <br> 
		E-Mail:<br> 
		<input type="email" name="email" > <br>
		Password:<br> 
		<input type="password" name="password" > <br>
		repeat Password :<br> 
		<input type="password" name="wdhpassword" > <br>
		<br> <input type="Submit" value="Registration">
	</form>
	
</body>
</html>





