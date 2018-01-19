
package com.ase2017.ats;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.routing.Router;

public class StudentApplication extends Application {

    
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
        
        Restlet AllStudent = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            	String message = StudentResource.allStudent();
                response.setEntity(message, MediaType.APPLICATION_XML);
                //response.setEntity(message, MediaType.TEXT_PLAIN);
            }
        };
        
        Restlet SingleStudent = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            	String studentId = (String) request.getAttributes().get("studentId");
                String message = StudentResource.singelStudent(studentId);
                response.setEntity(message, MediaType.APPLICATION_XML);
                //response.setEntity(message, MediaType.TEXT_PLAIN);
            }
        };
        
        Restlet AttendanceList = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            	String studentId = (String) request.getAttributes().get("studentId");
                String message = StudentResource.attendancelist(studentId);
                response.setEntity(message, MediaType.APPLICATION_XML);
                //response.setEntity(message, MediaType.TEXT_PLAIN);
            }
        };

        // Defines only one route
        router.attachDefault(AllStudent);
        router.attach("/test", HelloWorld);
        router.attach("/Hello/", HelloWorldResource.class);
        router.attach("/{studentId}/", SingleStudent);
        router.attach("/AttendanceList/{studentId}/", AttendanceList);
        
        

        return router;
    }
}

