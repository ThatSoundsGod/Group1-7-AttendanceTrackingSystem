package com.ase2017.ats;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Attandance {
	@Id @Index public String attendance_id; // qr code
	@Index public String attandance_student_id; //student id
	@Index public Long attendance_tutorial_id; //tutorial id
	@Index public Date attandance_date;
	public boolean attandance_attended;
	public boolean attendance_presented;
	
	//test
	
	public Attandance() {
	}
	
	public Attandance(String qr, String student_id, Long tutorial_id, Date date) {
		this.attendance_id = qr;
		this.attandance_student_id = student_id;
		this.attendance_tutorial_id = tutorial_id;
		this.attandance_date = date;
	}
	
	public Attandance(String qr, String student_id, Long tutorial_id, Date attandance_date, boolean attended, boolean presented) {
		this(qr, student_id,tutorial_id,attandance_date);
		this.attandance_attended = attended;
		this.attendance_presented = presented;
	}
	
	public static void createAttandance(String student_id, Long tutorial_id) {
		Attandance attandance;
		
		Tutorial tutorial = ObjectifyService.ofy().load().type(Tutorial.class).id(tutorial_id).now();
		
		//DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar calendarstart = Calendar.getInstance();
		Calendar calendarstop = Calendar.getInstance();
		calendarstart.setTime(tutorial.tutorial_startdate);
		calendarstop.setTime(tutorial.tutorial_stopdate);
		calendarstop.add(Calendar.DATE, 1);
		
        for (Date date = calendarstart.getTime(); calendarstart.before(calendarstop); calendarstart.add(Calendar.DATE, 7), date = calendarstart.getTime()) {
        	//System.out.println(date);
            UUID uuid = UUID.randomUUID();
            String qr = uuid.toString();
            attandance = new Attandance(qr, student_id, tutorial_id, date);
    		ObjectifyService.ofy().save().entity(attandance).now();
        }		
	}
	
	public static void deleteAttandance(String student_id) {
		List<Attandance> attandances = ObjectifyService.ofy()
				.load()
				.type(Attandance.class)
				.filter("attandance_student_id ==",student_id)
				.list();
		
		for (Attandance attandance : attandances) {
			ObjectifyService.ofy().delete().type(Attandance.class).id(attandance.attendance_id).now();
		}
		
	}
	
	public void verifyAttandance(String qr, boolean attended, boolean presented) {
		Attandance attandance = ObjectifyService.ofy().load().type(Attandance.class).id(qr).now();
		ObjectifyService.ofy().delete().type(Attandance.class).id(attandance.attendance_id).now();
		attandance = new Attandance(qr, attandance.attandance_student_id, attandance.attendance_tutorial_id, attandance.attandance_date, attended, presented);
		ObjectifyService.ofy().save().entity(attandance).now();
	}
}
