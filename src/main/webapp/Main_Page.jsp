<%-- //[START all]--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.ase2017.ats.Tutorial"%>
<%@ page import="com.ase2017.ats.Student"%>
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
					<li class="active"><a href="/Main_Page.jsp">Home</a></li>
					<li><a href="/Attendance_List.jsp">Attendance List</a></li>
					<li><a href="/User_Info.jsp">User Info</a></li>
					<li><a href="/Attendance_Log.jsp">Attendance Log</a></li>
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
				List<Tutorial> tutorials = ObjectifyService.ofy()
						.load()
						.type(Tutorial.class)
						.order("tutorial_number")
						.list();
				
				Student student = ObjectifyService.ofy().load().type(Student.class).id(user.getUserId()).now();
						
				if (tutorials.isEmpty()) {
				%>
				<!-- no Tutorials -->
				<h3>There are no Tutorial Groups.</h3>
				<%
				
				} else if (student != null && student.student_tutorialid != null) {
				%>
				<!-- Student is attending Tutorial -->
				<h3>You are currently attending this Tutorial:</h3>
				<%
					
					for(Tutorial tutorial:tutorials){
						if (tutorial.id.equals(student.student_tutorialid)){
							pageContext.setAttribute("tutorialnumber",tutorial.tutorial_number);
							pageContext.setAttribute("tutorname",tutorial.tutorial_tutor);
							pageContext.setAttribute("tutorialtime",tutorial.tutorial_time);
							pageContext.setAttribute("tutorialstartdate",dateoutputformat.format(tutorial.tutorial_startdate));
							pageContext.setAttribute("tutorialroom",tutorial.tutorial_roomnumber);
							pageContext.setAttribute("tutorialid",tutorial.id);
							%>
							
							<div class="well well-sm">
								<table class="table">
						            <thead>
						                <tr>
						                    <th>Group Number</th>
						                    <th>Tutor</th>
						                    <th>Time</th>
						                    <th>First Tutorial</th>
						                    <th>Room Number</th>
						                </tr>
						            </thead>
						            <tbody>
						                <tr>
						                    <td>${fn:escapeXml(tutorialnumber)}</td>
						                    <td>${fn:escapeXml(tutorname)}</td>
						                    <td>${fn:escapeXml(tutorialtime)}</td>
						                    <td>${fn:escapeXml(tutorialstartdate)}</td>
						                    <td>${fn:escapeXml(tutorialroom)}</td>
										</tr>
									</tbody>
								</table>
								<form action="/JoinTutorialServlet">
								  <input type="hidden" name="tutorialid" value="${fn:escapeXml(tutorialid)}">
								  <button type="submit" class="btn btn-primary" value="Submit">Leave Group</button>
								</form>

							</div>
							<%
							}	
						}
	
				} else {
				%>
				<!-- Student is not attending Tutorials -->
	        		<h3>The Tutorial Groups:</h3>
					<%
					for(Tutorial tutorial:tutorials){
						pageContext.setAttribute("tutorialnumber",tutorial.tutorial_number);
						pageContext.setAttribute("tutorname",tutorial.tutorial_tutor);
						pageContext.setAttribute("tutorialtime",tutorial.tutorial_time);
						pageContext.setAttribute("tutorialstartdate",dateoutputformat.format(tutorial.tutorial_startdate));
						pageContext.setAttribute("tutorialroom",tutorial.tutorial_roomnumber);
						pageContext.setAttribute("tutorialid",tutorial.id);
					%>
					
					<div class="well well-sm">
						<table class="table">
				            <thead>
				                <tr>
				                    <th>Group Number</th>
				                    <th>Tutor</th>
				                    <th>Time</th>
				                    <th>First Tutorial</th>
				                    <th>Room Number</th>
				                </tr>
				            </thead>
				            <tbody>
				                <tr>
				                    <td>${fn:escapeXml(tutorialnumber)}</td>
				                    <td>${fn:escapeXml(tutorname)}</td>
				                    <td>${fn:escapeXml(tutorialtime)}</td>
				                    <td>${fn:escapeXml(tutorialstartdate)}</td>
				                    <td>${fn:escapeXml(tutorialroom)}</td>
								</tr>
							</tbody>
						</table>
						<form action="/JoinTutorialServlet">
						  <input type="hidden" name="tutorialid" value="${fn:escapeXml(tutorialid)}">
						  <button type="submit" class="btn btn-success" value="Submit">Join Group</button>
						</form>

					</div>
					<%
					}
				}
				%>
	</div>
	
	<!-- Tutor Stuff -->
	<div class="container-fluid">
		<h4>If you are a Tutor, you can create a new Tutorial Group here:</h4>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#popUpWindow">Create new Group</button>
		<br>
		<br>
		
		<%-- PopupWindow --%>
		<div class="modal fade" id="popUpWindow" tabindex="-1" role="dialog"
			aria-labelledby="popUpWindowLongTitle" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">

					<!-- header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>

						</button>
						<h3 class="modal-title">Create new Tutorial Group</h3>
					</div>

					<!-- body (form) -->
					<div class="modal-body">
						<form action="/CreateTutorialServlet" role="form">
							<div class="form-group">
								Group Number: <input type="number" class="form-control" name="tutorialnumber" min="1" max="20">
							</div>
							<div class="form-group">
								Tutor Name: <input type="text" class="form-control" name="tutorname"
									placeholder="Tutorname">
							</div>
							<div class="form-group">
								Date of First Tutorial: <input type="date" class="form-control" name="startdate"
									placeholder="bday">
							</div>
							<div class="form-group">
								Date of Last Tutorial: <input type="date" class="form-control" name="stopdate"
									placeholder="bday">
							</div>
							<div class="form-group">
								Time of Tutorial: <input type="time" class="form-control" name="tutorialtime"> 
							</div>
							<div class="form-group">
								Room Number: <input type="text" class="form-control" name="roomnumber" placeholder="Room Number"> 
							</div>
							<div class="form-group">
								Tutor-Key: <input type="password" class="form-control" name="tutorkey"
									placeholder="TutorKey">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">Create Tutorial Group</button>
							</div>
						</form>
					</div>

					<!-- button 
					<div class="modal-footer">
						<a class="btn btn-primary btn-block" href="/CreateTutorialServlet">Create Tutorial Group</a>
					</div> -->

				</div>
			</div>
		</div>
		<%-- PopupWindow END --%>
	</div>
		<%
			} else {
		%>
	<div class="container-fluid">
		<h2>Please Login to Choose a Tutorial Group</h2>
		<%
			}
		%>
		
	</div>
</body>
</html>







