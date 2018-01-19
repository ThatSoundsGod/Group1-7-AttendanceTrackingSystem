
package com.ase2017.ats;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.routing.Router;

import com.googlecode.objectify.ObjectifyService;

public class AttendanceLogApplication extends Application {

    
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());
        
        Restlet HelloWorld = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
                String message = "Hello World!";
                response.setEntity(message, MediaType.TEXT_PLAIN);
            }
        };
        
        Restlet AttendanceLog = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            	String tutorkey = (String) request.getAttributes().get("TutorKey");
            	String message = "";
            	if (tutorkey.equals("123456"))
            	{
	            	String qr = (String) request.getAttributes().get("qr");
	            	String presented = (String) request.getAttributes().get("presented");
	            	
	            	Attandance attandance = ObjectifyService.ofy().load().type(Attandance.class).id(qr).now();
	            	attandance.attandance_attended = true;
	            	if (presented.equals("true")) {
	            		attandance.attendance_presented = true;
	            	}
	            	ObjectifyService.ofy().delete().type(Attandance.class).id(qr).now(); // delete
	            	ObjectifyService.ofy().save().entity(attandance).now();				// save
	                message = "Attendance Saved!" ;
            	} else {
            		message = "FAIL!";
            	}
                response.setEntity(message, MediaType.TEXT_PLAIN);
            }
        };

        // Defines only one route

        router.attach("/test", HelloWorld);
        router.attach("/{TutorKey}/{qr}/{presented}/", AttendanceLog);

        
     
        

        return router;
    }
}

