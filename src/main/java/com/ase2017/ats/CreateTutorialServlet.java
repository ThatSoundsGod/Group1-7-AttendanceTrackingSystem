package com.ase2017.ats;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

public class CreateTutorialServlet extends HttpServlet {

	private String Key = "123456";
	private boolean createEntity = true;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		createEntity = true;
		
		Tutorial tutorial;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		int tutorial_number = 0;
		try {
			tutorial_number = Integer.parseInt(req.getParameter("tutorialnumber")); // convert to integer
		}catch (NumberFormatException e) {
			System.out.println("Cancel because tutorialnumber parser");
			e.printStackTrace();
			createEntity = false;
		}
		
		String tutorial_roomnumber = req.getParameter("roomnumber");
		String tutorial_time = req.getParameter("tutorialtime");
		String tutorial_tutor = req.getParameter("tutorname");
		
		Date tutorial_startdate = null;
		try {
			tutorial_startdate = df.parse(req.getParameter("startdate")); //convert to date
		} catch (ParseException e) {
			System.out.println("Cancel because Startdate");
			e.printStackTrace();
			createEntity = false;
		} 
		
		Date tutorial_stopdate = null;
		try {
			tutorial_stopdate = df.parse(req.getParameter("stopdate")); //convert to date
		} catch (ParseException e) {
			System.out.println("Cancel because stopdate");
			e.printStackTrace();
			createEntity = false;
		}  
		
		String tutorial_tutorkey = req.getParameter("tutorkey");
		
		List<Tutorial> ts = ObjectifyService.ofy()
				.load()
				.type(Tutorial.class)
				.list();
		
		for (Tutorial t:ts)	//testing for equal tutorial number
		{
			System.out.println("t.tutorial_number " +t.tutorial_number+" == tutorial_number" + tutorial_number);
			if(t.tutorial_number == tutorial_number) {
				System.out.println("Cancel because same tnumber");
				createEntity = false;
			}
			
		}

		if (tutorial_tutorkey.equals(Key) && createEntity == true) {
			tutorial = new Tutorial(tutorial_number, tutorial_roomnumber, tutorial_time, tutorial_tutor,
					tutorial_startdate, tutorial_stopdate);

			ObjectifyService.ofy().save().entity(tutorial).now();
		}
		resp.sendRedirect("/Main_Page.jsp");

	}

}
