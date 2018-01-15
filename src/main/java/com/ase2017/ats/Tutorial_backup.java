/*package com.ase2017.ats;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tutorial_backup {
	@Id public Long id;
	@Index public String tutorial_tutor;
	@Index public String tutorial_number;
	public String tutorial_time;
	public String tutorial_roomnumber;
	public String tutorial_startdate;
	public String tutorial_stopdate;
	public List<Student> tutorial_members = new ArrayList<Student>();


	public Tutorial_backup() {	
	}
	
	public Tutorial_backup(String Tutorialnumber, String Roomnumber, String Time, String Tutor, String Startdate, String Stopdate) {
		this.tutorial_number = Tutorialnumber;
		this.tutorial_roomnumber = Roomnumber;
		this.tutorial_time = Time;
		this.tutorial_tutor = Tutor;
		this.tutorial_startdate = Startdate;
		this.tutorial_stopdate = Stopdate;
	}
	
}
*/