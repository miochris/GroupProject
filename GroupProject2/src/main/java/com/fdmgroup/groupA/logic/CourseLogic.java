package com.fdmgroup.groupA.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.model.Course;

public class CourseLogic {

	@Autowired
	private CourseDAO cDao;
	
	SimpleDateFormat formatter;

	Date startDateInDate;
	Date endDateInDate;
	Course course;
	
	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}
	
	public void createCourse(String courseName, String startDate, String endDate, double price) {
		 formatter = new SimpleDateFormat("yyyy-MM-dd");
		 startDateInDate=null;
		 endDateInDate=null;

        try {
            startDateInDate = formatter.parse(startDate);
			endDateInDate = formatter.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		 course = new Course(startDateInDate, endDateInDate, courseName, price);
		cDao.addCourse(course);
	}
	
	public void changePrice(int courseId, double newPrice) {
		Course course = cDao.getCourse(courseId);
		course.setPrice(newPrice);
		cDao.updateCourse(course);
	}
	
}
