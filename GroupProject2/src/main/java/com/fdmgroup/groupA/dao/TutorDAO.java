package com.fdmgroup.groupA.dao;

import com.fdmgroup.groupA.model.Tutor;

public interface TutorDAO  extends UserDAO {
	
	
	public Tutor findTutorByLesson(String lessonName);
	
	public double getPercentageByTutorName(String username);
}
