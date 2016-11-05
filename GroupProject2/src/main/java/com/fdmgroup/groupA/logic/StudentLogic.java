package com.fdmgroup.groupA.logic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.groupA.dao.DownloadDAO;
import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.model.Grade;

public class StudentLogic {

	@Autowired
	private GradeDAO gDao;
	@Autowired
	private DownloadDAO dDao;
	
	private Map<Integer, String> StudentGradeMap ;
	
	public Map<Integer, String> createStudentGradeMap(String username) {
		 StudentGradeMap = new HashMap<Integer, String>();
		for(Grade g : gDao.findGradeIDByStudent(username)) {
			if(dDao.getFilePathByUserNameAndType(g.getStudent().getUsername(), g.getLesson().getId()) == "0") {
				StudentGradeMap.put(g.getId(), "0");								
			} else {
				StudentGradeMap.put(g.getId(), "1");
			}
		}
		return StudentGradeMap;
	}

	public GradeDAO getgDao() {
		return gDao;
	}

	public void setgDao(GradeDAO gDao) {
		this.gDao = gDao;
	}

	public DownloadDAO getdDao() {
		return dDao;
	}

	public void setdDao(DownloadDAO dDao) {
		this.dDao = dDao;
	}

	public Map<Integer, String> getStudentGradeMap() {
		return StudentGradeMap;
	}

	public void setStudentGradeMap(Map<Integer, String> studentGradeMap) {
		StudentGradeMap = studentGradeMap;
	}
	
}
