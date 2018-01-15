/*package com.ase2017.ats;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import com.ase2017.ats.Student;
import com.googlecode.objectify.ObjectifyService;
import java.util.List;

public class StudentResource extends ServerResource {

	@Get("xml")
	public String represent() {
		List<Student> students = ObjectifyService.ofy()
				.load()
				.type(Student.class)
				.list();
		
		
		
		
		String ret = "";
		ret += "<tutorium xmlns=\"com.ase2017.ats\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://192.168.0.100:8080/Student.xsd\">\n";
		for (Student student : students) {
			ret += "<student>\n";
			ret += "<id>" + student.id + "</id>\n";
			ret += "<firstname>" + student.student_firstname + "</firstname>\n";
			ret += "<lastname>" + student.student_lastname + "</lastname>\n";
			ret += "<student_id>" + student.student_id + "</student_id>\n";
			ret += "<email>" + student.student_email + "</email>\n";
			ret += "<password>" + student.student_password + "</password>\n";
			ret += "</student>";
		
		}
		ret += "</tutorium>";
		return ret;
	}
}
*/