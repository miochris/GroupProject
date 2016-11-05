package com.fdmgroup.groupA.dao;

import java.util.List;

import com.fdmgroup.groupA.model.Grade;

public interface GradeDAO {
	
	void addNewGrade(Grade grade);
	void updateGrade (Grade grade);
	Grade findGradeByStudentAndLesson(String studentName, String lessonName);
	List<Grade> findGradeIDByStudent(String studentName);
	List<Grade> listAllGradesForEachLesson(int lessonId);
	Grade getGradeByGradeId(int id);
	void removeGrade(int gradeId);

}
