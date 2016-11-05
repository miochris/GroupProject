package com.fdmgroup.groupA.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.logic.CourseLogic;
import com.fdmgroup.groupA.model.CourseAddRequest;

@Controller
public class CourseController {	
	
	@Autowired
	RequestDAO rDao;
	@Autowired
	CourseDAO cDao;
	@Autowired
	CourseLogic courseLogic;
	
	public void setCourseLogic(CourseLogic courseLogic) {
		this.courseLogic = courseLogic;
	}

	public static final Logger logger = Logger.getLogger(CourseController.class);
	
	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}

	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}
	
	
	
	@RequestMapping(value="/createCourse", method=RequestMethod.GET)
	public String goToCreateCourse(HttpServletRequest request, Model model, CourseAddRequest courseAddRequest) {
		logger.debug("Debug starts");
		model.addAttribute("courseAddRequest",new CourseAddRequest());
		logger.info("tutor wants to request: " + courseAddRequest);
		logger.debug("Debug ends");
		return "protected/tutor/lessons/createCourse";
		
	}
	
	@RequestMapping(value="/createCourse", method=RequestMethod.POST)
	public String CreateCourse(HttpServletRequest request,Model model,CourseAddRequest courseAddRequest){
		logger.debug("Debug starts");
		rDao.addRequest(courseAddRequest);
		logger.info("addRequest method");
		HttpSession session = request.getSession();
		logger.info("http request.getSession");
		session.setAttribute("courseAddRequest", courseAddRequest);
		logger.info("session.setAttribute(courseAddRequest)");
		logger.debug("Debug ends");
		return "protected/tutor/lessons/createCourseRequestSent";
	}
	
	@RequestMapping(value="/deleteCourse", method=RequestMethod.POST)
	public String deleteCourse(Model model, @RequestParam int courseId) {
		logger.debug("Debug starts");
		cDao.deleteCourse(courseId);
		logger.info("Admin Deleted A Course with id: " + courseId);
		logger.debug("Debug ends");
		return "protected/admin/adminView";
	}
	
	@RequestMapping(value="/responseCreateCourse", method=RequestMethod.POST)
	public String ResponseReq(Model model, @RequestParam int requestId, @RequestParam String submit, @RequestParam String startDate, @RequestParam String endDate, @RequestParam double price) {
		logger.debug("Debug starts");
		if(submit.equals("Approve")) {
			logger.info("Request to create course" + requestId + " is APPROVED." );
			courseLogic.createCourse(((CourseAddRequest)rDao.getRequestById(requestId)).getCourseName(), startDate, endDate, price);
		}
		logger.info(requestId + " :Request is deleted");
		rDao.deleteRequest(requestId);
		
		model.addAttribute("type", "C");
		logger.debug("Debug ends");
		return "protected/admin/request/viewRequest";
	}
	
	@RequestMapping(value="/createCourseWithoutReq", method=RequestMethod.GET)
	public String goToCreateCourse(Model model) {
		logger.debug("Debug starts");
		logger.info("Go to CreateCourse Page");
		logger.debug("Debug ends");
		return "protected/admin/course/createCourse";
	}
	
	@RequestMapping(value="/createCourseWithoutReq", method=RequestMethod.POST)
	public String createCourseWithoutReq(Model model, @RequestParam String courseName, @RequestParam String startDate, @RequestParam String endDate,@RequestParam double price) {
		logger.debug("Debug starts");
		courseLogic.createCourse(courseName, startDate, endDate, price);	
		logger.info("Course was created");
		logger.debug("Debug ends");
		return"protected/admin/course/createCourseSuccess";
	}
	
	@RequestMapping(value="/changePrice", method=RequestMethod.POST)
	public String changePassword(Model model, @RequestParam int courseId, @RequestParam double newPrice) {
		logger.debug("Debug starts");
		courseLogic.changePrice(courseId, newPrice);
		logger.info("Course Price has been updated");
		model.addAttribute("type","C");
		model.addAttribute("coursesList",cDao.listCourses());
		logger.info("Courselist preview");
		logger.debug("Debug ends");
		return "protected/admin/adminView";
	}
}
