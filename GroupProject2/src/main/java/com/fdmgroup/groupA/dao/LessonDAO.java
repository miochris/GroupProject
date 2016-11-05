package com.fdmgroup.groupA.dao;

import java.util.List;

import com.fdmgroup.groupA.model.Lesson;

public interface LessonDAO {
	
	void addLesson(Lesson lesson);
	
	void updateLesson(Lesson lesson);
	
	Lesson getLesson(int lessonID);
	
	void removeLesson (int lessonID);
	
	List<Lesson> listLessons();
	
	Lesson getLessonByLessonName(String lessonName);
}
