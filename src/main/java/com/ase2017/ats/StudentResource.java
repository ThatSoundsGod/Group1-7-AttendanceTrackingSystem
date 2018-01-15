package com.ase2017.ats;

import org.restlet.resource.Get;
//import org.restlet.resource.ServerResource;
import com.ase2017.ats.Student;
import com.googlecode.objectify.ObjectifyService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class StudentResource {
	
	@Get("xml")
	public static String allStudent() {
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
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
			ret += "<tutor>" +tutorial.tutorial_tutor+ "</tutor>";
			ret += "<roomnumber>" +tutorial.tutorial_roomnumber+ "</roomnumber>";
			ret += "<lectures>\n";
			
			int lecture = 1;
			
			List<Attandance> attandances = ObjectifyService.ofy()
					.load()
					.type(Attandance.class)
					//.filter("attandance_student_id",student.student_id) //is not working when deployed!! but is working on local server! know idea why 
					.order("attandance_date")
					.list();
			
	        for (Attandance attandance:attandances) {
	        	if (attandance.attandance_student_id.equals(student.student_id)) { //not needed when filter is working!!
		            ret += "<lecture" + lecture +">\n";
		            ret += "<date>" +df.format(attandance.attandance_date)+ "</date>\n";
		            ret += "<qr>" + attandance.attendance_id + "</qr>\n";
		            ret += "</lecture"+ lecture +">\n";
		            lecture += 1;
	        	}
	            
	        }
	        
	        ret += "</lectures>\n";
			ret += "</tutorial>\n";
			ret += "</student>\n";
		
		}
		ret += "</asetutorials>";
		return ret;
	}

	@Get("xml")
	public static String singelStudent(String studentId) {
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		Student student = ObjectifyService.ofy().load().type(Student.class).id(studentId).now();
		
		String ret = "";
		ret += "<asetutorials xmlns=\"com.ase2017.ats\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://192.168.0.100:8080/Student.xsd\">\n";
		ret += "<student>\n";
		ret += "<studentid>" + student.student_id + "</studentid>\n";
		ret += "<tutorial>\n";
		
		Tutorial tutorial = ObjectifyService.ofy().load().type(Tutorial.class).id(student.student_tutorialid).now();
		
		ret += "<tutorialnumber>" +tutorial.tutorial_number+ "</tutorialnumber>\n";
		ret += "<tutor>" +tutorial.tutorial_tutor+ "</tutor>\n";
		ret += "<roomnumber>" +tutorial.tutorial_roomnumber+ "</roomnumber>\n";
		ret += "<lectures>\n";
		
		int lecture = 1;
		
		List<Attandance> attandances = ObjectifyService.ofy()
				.load()
				.type(Attandance.class)
				//.filter("attandance_student_id",student.student_id) //is not working when deployed!! but is working on local server! know idea why
				.order("attandance_date")
				.list();
		
        for (Attandance attandance:attandances) {
        	if (attandance.attandance_student_id.equals(student.student_id)) { //not needed when filter is working!!
	            ret += "<lecture" + lecture +">\n";
	            ret += "<date>" +df.format(attandance.attandance_date)+ "</date>\n";
	            ret += "<qr>" + attandance.attendance_id + "</qr>\n";
	            ret += "</lecture"+ lecture +">\n";
	            lecture += 1;
        	} 
        }
        ret += "</lectures>\n";
		ret += "</tutorial>\n";
		ret += "</student>\n";
		ret += "</asetutorials>";
		return ret;
	}

	@Get("xml")
	public static String attendancelist(String studentId) {
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		Student student = ObjectifyService.ofy().load().type(Student.class).id(studentId).now();
		
		String ret = "";
		ret += "<attendancelist xmlns=\"com.ase2017.ats\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://192.168.0.100:8080/Student.xsd\">\n";
		ret += "<student>\n";
		ret += "<studentid>" + student.student_id + "</studentid>\n";
		ret += "<tutorial>\n";
		
		Tutorial tutorial = ObjectifyService.ofy().load().type(Tutorial.class).id(student.student_tutorialid).now();
		
		ret += "<tutorialnumber>" +tutorial.tutorial_number+ "</tutorialnumber>\n";
		ret += "<lectures>\n";
		
		int lecture = 1;
		
		List<Attandance> attandances = ObjectifyService.ofy()
				.load()
				.type(Attandance.class)
				//.filter("attandance_student_id",student.student_id) //is not working when deployed!! but is working on local server! know idea why
				.order("attandance_date")
				.list();
		
        for (Attandance attandance:attandances) {
        	if (attandance.attandance_student_id.equals(student.student_id)) { //not needed when filter is working!!
	            ret += "<lecture" + lecture +">\n";
	            ret += "<date>" +df.format(attandance.attandance_date)+ "</date>\n";
	            ret += "<attended>" + attandance.attandance_attended +"</attended>\n";
	            ret += "<presented>" + attandance.attendance_presented +"</presented>\n";
	            ret += "</lecture"+ lecture +">\n";
	            lecture += 1;
        	} 
        }
        ret += "</lectures>\n";
		ret += "</tutorial>\n";
		ret += "</student>\n";
		ret += "</attendancelist>";
		return ret;
	}

}
