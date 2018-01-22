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
<!-- <link rel="shortcut icon" type="image/x-icon; charset=binary"
href="TUM_Web_Logo_blau.ico" />  -->
<!-- <link rel="shortcut icon" type="image/x-icon; charset=binary"
href="favicon.ico" /> -->

<link rel="apple-touch-icon" sizes="57x57" href="/image/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="/image/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="/image/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="/image/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="/image/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="/image/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="/image/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="/image/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="/image/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="/image/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="/image/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="/image/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="/image/favicon-16x16.png">
<link rel="manifest" href="/image/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/image/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
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
				<a href="/Main_Page.jsp" class="navbar-brand">Attendance Tracking System</a>
			</div>

			<!-- Menu Items -->
			<div>
				<ul class="nav navbar-nav">
					<li><a href="/Main_Page.jsp">Home</a></li>
					<li><a href="/Attendance_List.jsp">Personal Attendance Record</a></li>
					<li class="active"><a href="/Attendance_List_all.jsp">Overall Attendance Records</a></li>
					<li><a href="/User_Info.jsp">User Info</a></li>
				<!-- 	<li><a href="/Attendance_Log.jsp">Attendance Log</a></li>  -->
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
		<%
			if (user != null) {
				
				List<Attandance> attandances = ObjectifyService.ofy()
						.load()
						.type(Attandance.class)
						//.filter("attandance_student_id", user.getUserId())
						.order("attandance_date")
						.list();
						
				if (attandances.isEmpty()) {
				%>
				<!-- no Attendance Records -->
				<h3>There are no Attendance Records!</h3>
				<%
				
				} else {
				%>
				<!-- Attendances -->
				
				<h3>Select an E-mail to list the Attendance Records!</h3>
				
				<form action="/Attendance_List_all.jsp" method="post">
					<div class="form-group row">
						<div class="col-xs-8">
					    <select class="form-control form-control-lg" id="SelectEmail" name="id">
					    	<option disabled selected> -- select an E-mail Address -- </option>
						    <%
						    List<Student> students = ObjectifyService.ofy()
							.load()
							.type(Student.class)
							.list();
						    for (Student student:students){
						    	pageContext.setAttribute("email",student.student_email);
						    	pageContext.setAttribute("studentid",student.student_id);
						    %>
					      	<option value="${fn:escapeXml(studentid)}">${fn:escapeXml(email)}</option>
					    	<%}%> 
					    </select>
					    </div>
					</div>
					<div class="form-group">
			    		<button type="submit" class="btn btn-primary">List Records</button>
			    	</div>
				</form>
				<%if (request.getParameter("id") != null){
					Student student2 = ObjectifyService.ofy().load().type(Student.class).id(request.getParameter("id")).now();
					pageContext.setAttribute("nickname",student2.student_email);
				%>	
				<h3>Here are the Attendance Records for ${fn:escapeXml(nickname)}:</h3>
				
				<div class="well">
				<%
					int i = 1;
					int nocontent = 0;
					for(Attandance attandance:attandances){
						if (attandance.attandance_student_id.equals(request.getParameter("id"))){
							pageContext.setAttribute("number",i);
							i += 1;
							pageContext.setAttribute("date",dateoutputformat.format(attandance.attandance_date));
					%>
							<table class="table">
					            <thead>
					                <tr>
					                    <th>Number</th>
					                    <th>Date</th>
					                    <th class="text-center">Attended</th>
					                    <th class="text-center">Presented</th>
					                </tr>
					            </thead>
					            <tbody>
					                <tr>
					                    <td>${fn:escapeXml(number)}</td>
					                    <td>${fn:escapeXml(date)}</td>
					                    <%if (attandance.attandance_attended){ %>
					                    	<td class="text-center"><span class= "glyphicon glyphicon-ok" style="font-size:20px;color:green"></span></td>
					                    <%} else { %>
					                    	<td class="text-center"><span class= "glyphicon glyphicon-remove" style="font-size:20px;color:red"></span></td>
					                    <%}%>
					                    <%if (attandance.attendance_presented){ %>
					                    	<td class="text-center"><span class= "glyphicon glyphicon-ok" style="font-size:20px;color:green"></span></td>
					                    <%} else { %>
					                    	<td class="text-center"><span class= "glyphicon glyphicon-remove" style="font-size:20px;color:red"></span></td>
					                    <%}%>
									</tr>
								</tbody>
							</table>
						<%} else {nocontent++;}%>
					<%} if (nocontent == attandances.size()){ %>
					There are no Attendance Records for this User!
					<%}%>
				</div>
				<%}%>	
				<%}%>
	</div>
		<%
			} else {
		%>
	<div class="container-fluid">
		<h2>Please Login to see the Attendance Records</h2>
		<%
			}
		%>
		
	</div>
</body>
</html>







