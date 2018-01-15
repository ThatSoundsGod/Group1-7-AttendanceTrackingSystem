/*package com.ase2017.ats;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import com.ase2017.ats.Student;
import com.googlecode.objectify.ObjectifyService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StudentResource_backup2 extends ServerResource {

	@Get("xml")
	public String represent() {
		List<Student> students = ObjectifyService.ofy()
				.load()
				.type(Student.class)
				.list();

		String ret = "";
		ret += "<asetutorials xmlns=\"com.ase2017.ats\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://192.168.0.100:8080/Student.xsd\">\n";
		for (Student student : students) {
			ret += "<student>\n";
			ret += "<studentid>" + student.student_id + "</studentid>\n";
			ret += "<tutorial>\n";
			
			Tutorial tutorial = ObjectifyService.ofy().load().type(Tutorial.class).id(student.student_tutorialid).now();
			
			ret += "<tutorialnumber>" +tutorial.tutorial_number+ "</tutorialnumber>";
			
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			Calendar calendarstart = Calendar.getInstance();
			Calendar calendarstop = Calendar.getInstance();
			calendarstart.setTime(tutorial.tutorial_startdate);
			calendarstop.setTime(tutorial.tutorial_stopdate);
			calendarstop.add(Calendar.DATE, 1);
			
			int lecture = 1;
			
	        for (Date date = calendarstart.getTime(); calendarstart.before(calendarstop); calendarstart.add(Calendar.DATE, 7), date = calendarstart.getTime()) {
	            System.out.println(date);
	            ret += "<lecture" + lecture +">\n";
	            ret += "<date>" +df.format(date)+ "</date>\n";
	            UUID uuid = UUID.randomUUID();
	            String qr = uuid.toString();
	            ret += "<qr>" + qr + "</qr>\n";
	            ret += "</lecture"+ lecture +">\n";
	            lecture += 1;
	            
	        }		
			ret += "</tutorial>\n";
			ret += "</student>\n";
		
		}
		ret += "</asetutorials>";
		return ret;
	}
}
*/
