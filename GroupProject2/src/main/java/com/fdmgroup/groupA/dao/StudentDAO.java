package com.fdmgroup.groupA.dao;

import java.util.List;

import com.fdmgroup.groupA.model.Student;

public interface StudentDAO extends UserDAO{
	
	public List<Student> listStudentByCourseId(int id);
}
