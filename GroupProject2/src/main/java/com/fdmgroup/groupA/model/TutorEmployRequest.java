package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TutorEmployRequest extends Request {
	
	@Column
	private String preferredCourseName;

	public TutorEmployRequest(String username, String type, String preferredCourseName, String description) {
		super(username, type, description);
		this.preferredCourseName = preferredCourseName;
	}

	public TutorEmployRequest() {
		super();
	}

	public String getPreferredCourseName() {
		return preferredCourseName;
	}

	public void setPreferredCourseName(String preferredCourseName) {
		this.preferredCourseName = preferredCourseName;
	}
	
	
}
