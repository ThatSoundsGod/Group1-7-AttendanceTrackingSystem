package com.ase2017.ats;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import java.lang.String;
//import java.util.Date;
//import java.util.List;

@Entity
public class Student {
	@Index public String student_email;
	@Id @Index public String student_id;
	@Index public String student_nickname;
	@Index public Long student_tutorialid;
	
	
	public Student() {
	}
	
	public Student(String id, String nickname, String email) {
		this.student_email = email;
		this.student_id = id;
		this.student_nickname = nickname;
		
	}
	
	public Student(String id, String nickname, String email, Long tutorialid) {
		this(id,nickname,email);
		//student_tutorialkey = Key.create(Tutorial.class, tutorialid);
		this.student_tutorialid = tutorialid;
		
	}
	
}
