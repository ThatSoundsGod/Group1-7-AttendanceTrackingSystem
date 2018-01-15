/*package com.ase2017.ats;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;



public class RegistrationServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Student student;
		
		String student_firstname = req.getParameter("firstname");
		String student_lastname = req.getParameter("lastname");
		String student_id = req.getParameter("id");
		String student_email = req.getParameter("email");
		String student_password = req.getParameter("password");
		
		student = new Student(student_firstname,student_lastname,student_id,student_email,student_password);
		
		ObjectifyService.ofy().save().entity(student).now();
		resp.sendRedirect("/Login_Page.jsp");
		
	}

}
*/