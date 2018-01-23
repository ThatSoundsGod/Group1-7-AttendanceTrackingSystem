package com.ase2017.ats;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tutorial {
	@Id @Index public Long id;
	@Index public String tutorial_tutor;
	@Index public int tutorial_number;
	public String tutorial_time;
	public String tutorial_roomnumber;
	public Date tutorial_startdate;
	public Date tutorial_stopdate;
	



	public Tutorial() {	
	}
	
	public Tutorial(int Tutorialnumber, String Roomnumber, String Time, String Tutor, Date Startdate, Date Stopdate) {
		this.tutorial_number = Tutorialnumber;
		this.tutorial_roomnumber = Roomnumber;
		this.tutorial_time = Time;
		this.tutorial_tutor = Tutor;
		this.tutorial_startdate = Startdate;
		this.tutorial_stopdate = Stopdate;
	}
	
}