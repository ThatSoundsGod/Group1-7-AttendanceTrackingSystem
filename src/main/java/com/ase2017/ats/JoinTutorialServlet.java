package com.ase2017.ats;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.googlecode.objectify.ObjectifyService;

public class JoinTutorialServlet extends HttpServlet {

	private boolean joinTutorial = true;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Student student;

		Long tutorial_id = null;
		try {
			tutorial_id = Long.parseLong(req.getParameter("tutorialid")); // convert to integer
		} catch (NumberFormatException e) {
			System.out.println("Cancel because tutorialid parser");
			e.printStackTrace();
			joinTutorial = false;
		}

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		//System.out.println("Hier die User E-mail:" + user.getEmail());
		//System.out.println("Hier die User ID:" + user.getUserId());
		//System.out.println("Hier die User Nickname:" + user.getNickname());

		student = ObjectifyService.ofy().load().type(Student.class).id(user.getUserId()).now();

		//System.out.println("student.student_tutorialid"+student.student_tutorialid+ "== tutorial_id"+ tutorial_id);
		
		if (student == null && joinTutorial) { // Student not in objectify
			//System.out.println("Student not in objectify");
			student = new Student(user.getUserId(), user.getNickname(), user.getEmail(), tutorial_id);
			ObjectifyService.ofy().save().entity(student).now();
			Attandance.createAttandance(user.getUserId(), tutorial_id);
			
		} else if ((student.student_tutorialid == null ||!student.student_tutorialid.equals(tutorial_id)) && joinTutorial) { // student wants to join Tutorial Group
			//System.out.println("student wants to join Tutorial Group");
			ObjectifyService.ofy().delete().type(Student.class).id(user.getUserId()).now();
			student = new Student(user.getUserId(), user.getNickname(), user.getEmail(), tutorial_id);
			ObjectifyService.ofy().save().entity(student).now();
			Attandance.createAttandance(user.getUserId(), tutorial_id);
			
			
		} else if (student.student_tutorialid.equals(tutorial_id) && joinTutorial) { // student wants to leave Tutorial Group
			//System.out.println("student wants to leave Tutorial Group");
			ObjectifyService.ofy().delete().type(Student.class).id(user.getUserId()).now();
			student = new Student(user.getUserId(), user.getNickname(), user.getEmail());
			ObjectifyService.ofy().save().entity(student).now();
			Attandance.deleteAttandance(user.getUserId());
		}

		resp.sendRedirect("/Main_Page.jsp");

	}

}
