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
<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=UTF-8" />
<title>Login Page</title>
<link rel="shortcut icon" type="image/x-icon; charset=binary" href="TUM_Web_Logo_blau.ico" />
 
</head>

<body>
	<h1>Attendance Tracking System</h1>
	
	<h2>Login Page</h2>

	<form action="/LoginServlet">
		Student-ID:<br> 
		<input type="text" name="StudentID" value="Student-ID"> <br> 
		Password:<br> <input
		type="Password" name="Password" value="**********"> <br>
		<br> <input type="Submit" value="Login">
	</form>



	<table>
		<tr>
			<td colspan="2">New Studend? Register here:</td>
		</tr>
		<tr>
			<td><a href='/registration'>Registration</a></td>
		</tr>
	</table>
</body>
</html>





