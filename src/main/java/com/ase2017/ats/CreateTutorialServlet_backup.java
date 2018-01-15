/*package com.ase2017.ats;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class CreateTutorialServlet_backup extends HttpServlet {

	private String Key = "123456";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Tutorial tutorial;

		String tutorial_number = req.getParameter("tutorialnumber");
		String tutorial_roomnumber = req.getParameter("roomnumber");
		String tutorial_time = req.getParameter("tutorialtime");
		String tutorial_tutor = req.getParameter("tutorname");
		String tutorial_startdate = req.getParameter("startdate");
		String tutorial_stopdate = req.getParameter("stopdate");

		String tutorial_tutorkey = req.getParameter("tutorkey");
		
		List<Tutorial> ts = ObjectifyService.ofy()
				.load()
				.type(Tutorial.class)
				.list();
		
		for (Tutorial t:ts)
		{
			if(t.tutorial_number.equals(tutorial_number))
				resp.sendRedirect("/Main_Page.jsp");
		}

		if (tutorial_tutorkey.equals(Key)) {
			tutorial = new Tutorial(tutorial_number, tutorial_roomnumber, tutorial_time, tutorial_tutor,
					tutorial_startdate, tutorial_stopdate);

			ObjectifyService.ofy().save().entity(tutorial).now();
		}
		resp.sendRedirect("/Main_Page.jsp");

	}

}
*/
