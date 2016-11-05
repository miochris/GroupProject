package com.fdmgroup.groupA.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fdmgroup.groupA.controller.FundController;
import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.DownloadDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.Users;

public class AdminLogic {
	
	@Autowired
	private CourseDAO cDao;
	@Autowired
	@Qualifier("uDao")
	private UserDAO uDao;
	@Autowired
	private DownloadDAO dDao;
	private Map<Integer, Integer> courseLessonMap = new HashMap<Integer, Integer>();;
	private Course course;
	private Student student;
	
	public AdminLogic() {
		super();
	}


	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public void setdDao(DownloadDAO dDao) {
		this.dDao = dDao;
	}


	private static final Logger logger = Logger.getLogger(FundController.class);

	public Map<Integer, Integer> createCourseLessonMap() {
		logger.debug("Debug starts");

		Map<Integer, Integer> courseLessonMap = new HashMap<Integer, Integer>();

		for (Course c : cDao.listCourses()) {
				List<Lesson> lessonList = new ArrayList<Lesson>();
				logger.info("Created list of lessons"+lessonList);
			for (Tutor t : c.getTutorList()) {
				for (Lesson l : t.getLessonList()) {
					lessonList.add(l);
				}
			}
			courseLessonMap.put(c.getId(), lessonList.size());
			logger.info("****" +courseLessonMap);
		}
		logger.debug("Debug ends");
		return courseLessonMap;
		
	}
	
	public void changePassword(String username, String newPassword) {
		logger.debug("Debug starts");
		Users user = uDao.getUserByUserName(username);
		user.setPassword(newPassword);
		logger.info("New Password set fpr user: " +username);
		uDao.updateUser(user);
		logger.debug("Debug ends");
	}
	
	public void quitCourse(String username) {
		logger.debug("Debug starts");
		student = (Student) uDao.getUserByUserName(username);
		course = student.getCourse();
		course.getStudentList().remove(student);
		student.setCourse(null);
		uDao.updateUser(student);
		cDao.updateCourse(course);
		logger.info("Set student a Course"+ student+course);
		logger.debug("Debug ends");
		
	}
	
	public Map<String, String> createTutorCVMap() {
		logger.debug("Debug starts");
		Map<String, String> TutorCVMap = new HashMap<String, String>();
		for(Users t : uDao.getUserByType("T")) {
			if(dDao.getFilePathByUserNameAndType(t.getUsername(), 0) == "0") {
				logger.info("Retrieved file by username ");
				TutorCVMap.put(t.getUsername(), "0");								
			} else {
				logger.info("Retrieved file by username and type");
				TutorCVMap.put(t.getUsername(), dDao.getFilePathByUserNameAndType(t.getUsername(), 0));
			}
		}
		logger.debug("Debug ends");
		return TutorCVMap;
		
	}


	public Map<Integer, Integer> getCourseLessonMap() {
		return courseLessonMap;
	}


	public void setCourseLessonMap(Map<Integer, Integer> courseLessonMap) {
		this.courseLessonMap = courseLessonMap;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public CourseDAO getcDao() {
		return cDao;
	}


	public UserDAO getuDao() {
		return uDao;
	}

	public DownloadDAO getdDao() {
		return dDao;
	}
}
