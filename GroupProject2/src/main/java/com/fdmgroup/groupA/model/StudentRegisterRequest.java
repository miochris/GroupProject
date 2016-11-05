package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class StudentRegisterRequest extends Request {

	@Column
	private String courseName;

	public StudentRegisterRequest() {
		super();
	}

	public StudentRegisterRequest(String username, String type, String courseName, String description) {
		super(username, type, description);
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "StudentRegisterRequest [courseName=" + courseName + "]";
	}
	
}
