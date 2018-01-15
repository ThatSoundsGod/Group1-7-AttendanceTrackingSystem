/*package com.ase2017.ats;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

public class LoginServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		boolean passwordcorrect = false;
		
		String StudentID = req.getParameter("StudentID");
		String password = req.getParameter("Password");
		
		List<Student> students = ObjectifyService.ofy()
			.load()
			.type(Student.class)
			.list();
		
		for (Student student : students) {
			System.out.println(StudentID + "=" + student.student_id);
			if (student.student_id.equals(StudentID)) {
				System.out.println(password + "=" + student.student_password);
				if(student.student_password.equals(password)) {
					System.out.println("password true");
					passwordcorrect = true;
				}	
			}
		}
		
		if (passwordcorrect == true) {
			System.out.println("go to turorial" + passwordcorrect);
			resp.sendRedirect("/tutorial");
		}else {
			System.out.println("Loginpage" + passwordcorrect);
			resp.sendRedirect("/Login_Page.jsp");
		}
	}
}
*/