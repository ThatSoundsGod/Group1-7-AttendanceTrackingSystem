<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.ase2017.ats.Tutorial"%>
<%@ page import="com.ase2017.ats.Student"%>
<%@ page import="com.ase2017.ats.Attandance"%>
<%@ page import="com.googlecode.objectify.Key"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>
<%-- //[END imports]--%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>


<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Attendance Tracking System</title>
<link rel="shortcut icon" type="image/x-icon; charset=binary"
	href="TUM_Web_Logo_blau.ico" />
</head>

<body>

	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		DateFormat dateoutputformat = new SimpleDateFormat("dd. MMM yyyy");
	%>
	<!-- Navbar -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">

			<!-- Logo -->
			<div class="navbar-header">
				<a href="/Main_Page.jsp" class="navbar-brand">Attendant Tracking System</a>
			</div>

			<!-- Menu Items -->
			<div>
				<ul class="nav navbar-nav">
					<li><a href="/Main_Page.jsp">Home</a></li>
					<li><a href="/Attendance_List.jsp">Attendance List</a></li>
					<li><a href="/User_Info.jsp">User Info</a></li>
					<li class="active"><a href="/Attendance_Log.jsp">Attendance Log</a></li>
				</ul>
				<!--right align -->
				<ul class="nav navbar-nav navbar-right">
					<%
						if (user != null) {
					%>
					<li><a
						href="<%=userService.createLogoutURL(request.getRequestURI())%>">Logout</a></li>
					<%
						} else {
					%>
					<li><a
						href="<%=userService.createLoginURL(request.getRequestURI())%>">Login</a></li>
					<%
						}
					%>
				</ul>
			</div>

		</div>
	</nav>

	<!-- Main Stuff on Page -->
	<div class="container-fluid">

		<h1>Attendance Tracking System</h1>
		
		<form action="/AttendanceLogServlet" method="post">
		    <div><textarea name="log" rows="10" cols="100"></textarea></div>
		    <br />
		    <div class="form-group">
				<button type="submit" class="btn btn-primary">Log Attendance</button>
			</div>
		</form>		
	</div>
</body>
</html>







