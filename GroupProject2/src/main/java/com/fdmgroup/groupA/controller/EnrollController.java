package com.fdmgroup.groupA.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Grade;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.StudentRegisterRequest;
import com.fdmgroup.groupA.model.Tutor;

@Controller
public class EnrollController {

	@Autowired
	private RequestDAO rDao;
	@Autowired
	private CourseDAO cDao;
	@Autowired
	private StudentDAO sDao;
	@Autowired
	@Qualifier("uDao")
	private UserDAO uDao;
	@Autowired
	private TutorDAO tDao;
	@Autowired
	private GradeDAO gDao;
	
	private Course course;
	
	private List<Lesson> lessonList;
	private Admin admin ;
	private Grade grade;
	
	private static final Logger logger = Logger.getLogger(EmployController.class);
	
	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}
	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}
	public void setsDao(StudentDAO sDao) {
		this.sDao = sDao;
	}
	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}
	public void settDao(TutorDAO tDao) {
		this.tDao = tDao;
	}
	public void setgDao(GradeDAO gDao) {
		this.gDao = gDao;
	}
	@RequestMapping(value="/enrollRequest", method=RequestMethod.POST)
	public String sendEnrollRequest(Model model, StudentRegisterRequest req, @RequestParam String courseName, @RequestParam String username){
		logger.debug("Debug starts");
		Course course = cDao.getCourseByCourseName(courseName);
		Student student = (Student) sDao.getUserByUserName(username);
		logger.info("retrieve course and student");
		if(student.getBalance() >= course.getPrice()){
			rDao.addRequest(req);
			logger.info("student request to enroll sent");
			return "protected/student/academy/requestSent";
		}else{
			logger.info("student request to enroll denied");
			logger.debug("Debug ends");
			return "protected/student/academy/insufficientFunds";
		}
	}
	
	@RequestMapping(value="/approveEnroll", method=RequestMethod.POST)
	public String approveEnrollRequest(Model model, @RequestParam int requestId){
		logger.debug("Debug Starts");
		StudentRegisterRequest request = (StudentRegisterRequest) rDao.getRequestById(requestId);
		course = cDao.getCourseByCourseName(request.getCourseName());
		if(course.getTutorList().size() == 0) {
			logger.info("if no tutor in course, direct to no tutor page");
			logger.debug("Debug ends");
			return "protected/admin/request/noTutor";
			
		} else {
		Student student = (Student) sDao.getUserByUserName(request.getUsername());
		List<Tutor> tutorList=course.getTutorList();
		admin = (Admin) uDao.getUserByUserName("admin");
//		List<Users> userList = uDao.getUserByType("A");
//		Admin admin = (Admin) userList.get(0);
		 
		for(Tutor tutor: tutorList){
			
			lessonList=tutor.getLessonList();
			for(Lesson lesson: lessonList){
				grade=new Grade(student, lesson, -1);
				logger.info("add grade of student asignment");
				gDao.addNewGrade(grade);
			}
		}
		course.getStudentList().add(student);
		student.setCourse(course);
		student.setMark(0);

		student.pay(course.getPrice());
		admin.deposit(course.getPrice());

		for(Tutor t : tutorList) {
			
			admin.pay(course.getPrice()*(t.getPercentage()/100)*0.8);
			t.deposit(course.getPrice()*(t.getPercentage()/100)*0.8);
			logger.info("update tutor pay");
			tDao.updateUser(t);
			
		}
		logger.info("Course Details Updated");
		cDao.updateCourse(course);
		logger.info("Student Details Updated");
		sDao.updateUser(student);
		logger.info("Admin Details Updated");
		uDao.updateUser(admin);
		logger.info("Request is Deleted by ID.");
		rDao.deleteRequest(requestId);
		
		model.addAttribute("type",request.getType());
		model.addAttribute("reqList",rDao.getRequestByType(request.getType()));
		logger.debug("Debug ends");
		return "protected/admin/request/viewRequest";
		}
	}
	
	@RequestMapping(value="/rejectEnroll", method=RequestMethod.GET)
	public String rejectRequest(Model model, @RequestParam int id){
		logger.debug("Debug starts");
		StudentRegisterRequest request = (StudentRegisterRequest) rDao.getRequestById(id);
		rDao.deleteRequest(id);
		logger.info("Student Enroll Request Deleted");
		model.addAttribute("type",request.getType());
		model.addAttribute("reqList",rDao.getRequestByType(request.getType()));
		logger.debug("Debug starts");
		return "protected/admin/request/responseRequest";
	}
}
