package com.fdmgroup.groupA.factory;

import com.fdmgroup.groupA.model.CourseAddRequest;
import com.fdmgroup.groupA.model.LessonAddRequest;
import com.fdmgroup.groupA.model.Request;
import com.fdmgroup.groupA.model.StudentRegisterRequest;
import com.fdmgroup.groupA.model.TutorEmployRequest;

public class RequestFactory {

	public Request createRequest(String username, String type, String arg, String description) {
		
		Request request = null;
		
		if(type==null) {
			request=null;
		} else if(type.equalsIgnoreCase("C")) {
			request = new CourseAddRequest(username, type, arg, description);
		} else if(type.equalsIgnoreCase("L")) {
			request = new LessonAddRequest(username, type, arg, description);
		} else if(type.equalsIgnoreCase("S")) {
			request = new StudentRegisterRequest(username, type, arg, description);
		} else if(type.equalsIgnoreCase("T")) {
			request = new TutorEmployRequest(username, type, arg, description);
		}
		
		return request;
	}
	
}
