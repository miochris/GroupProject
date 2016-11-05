package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LessonAddRequest extends Request {
	
	@Column
	private String lessonName;

	public LessonAddRequest() {
		super();
	}

	public LessonAddRequest(String username, String type, String lessonName, String description) {
		super(username, type, description);
		this.lessonName = lessonName;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
}
