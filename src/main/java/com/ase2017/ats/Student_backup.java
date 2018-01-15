package com.ase2017.ats;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import java.lang.String;
//import java.util.Date;
//import java.util.List;

@Entity
public class Student_backup {
	@Id public Long id;
	public String student_firstname;
	@Index public String student_lastname;
	public String student_id;
	public String student_email;
	public String student_password;
	
	public Student_backup() {
	}
	
	
	public Student_backup(String firstname, String lastname, String id, String email, String password) {
		this.student_firstname = firstname;
		this.student_lastname = lastname;
		this.student_email = email;
		this.student_id = id;
		this.student_password = password;
	}
	
}
