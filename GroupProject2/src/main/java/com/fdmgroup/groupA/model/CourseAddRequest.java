package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CourseAddRequest extends Request {
	
	@Column
	private String courseName;

	public CourseAddRequest() {
		super();
	}

	public CourseAddRequest(String username, String type, String courseName, String description) {
		super(username, type, description);
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
