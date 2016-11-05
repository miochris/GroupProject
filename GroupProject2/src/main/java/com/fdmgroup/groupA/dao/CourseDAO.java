package com.fdmgroup.groupA.dao;

import java.util.List;

import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Tutor;

public interface CourseDAO {

	void addCourse(Course course);
	
	void deleteCourse(int id);
	
	void updateCourse(Course course);
	
	Course getCourse(int id);
	
	Course getCourseByCourseName(String name);
	
	List<Course> listCourses();
	
	void deleteStudentFromCourse(String username, String courseName);
	
	
	List<Tutor> listTutorByCourseName(String courseName);
}
