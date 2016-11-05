package com.fdmgroup.groupA.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.TutorEmployRequest;

@Controller
public class EmployController {
	private static final Logger logger = Logger.getLogger(EmployController.class);

	@Autowired
	RequestDAO rDao;
	@Autowired
	TutorDAO tDao;
	@Autowired
	CourseDAO cDao;
	@Autowired
	LessonDAO lDao;

	@RequestMapping(value = "/sendEmployRequest", method = RequestMethod.GET)
	public String goToApplication(Model model) {
		logger.debug("Debug starts");
		model.addAttribute("req", new TutorEmployRequest());
		logger.info("Employ Request Sent");
		logger.debug("Debug ends");
		return "tutor/account/employRequest";
	}

	@RequestMapping(value = "/sendEmployRequest", method = RequestMethod.POST)
	public String ApplicationSuccess(Model model, TutorEmployRequest req) {
		logger.debug("Debug starts");
		model.addAttribute("req", req);
		logger.info("Preview Application Success Page");
		logger.debug("Debug ends");
		return "tutor/account/RequestSent";
	}

	@RequestMapping(value = "/approveEmployRequest", method = RequestMethod.POST)
	public String ApproveEmployRequest(Model model, @RequestParam int requestId, @RequestParam String courseName) {
		logger.debug("Debug starts");
		TutorEmployRequest req = (TutorEmployRequest) rDao.getRequestById(requestId);
		Tutor tutor = (Tutor) tDao.getUserByUserName(req.getUsername());
		Course course = cDao.getCourseByCourseName(courseName);
		logger.info("retreived Dao needed");
		tutor.setHired(true);
		tutor.setCourse(course);
		course.getTutorList().add(tutor);
		cDao.updateCourse(course);
		logger.info("Course Updated");
		tDao.updateUser(tutor);
		logger.info("User Updated");
		model.addAttribute("type", req.getType());
		rDao.deleteRequest(requestId);
		logger.info("Request Deleted");
		model.addAttribute("reqList", rDao.getRequestByType(req.getType()));
		model.addAttribute("coursesList", cDao.listCourses());
		logger.debug("Debug ends");
		return "protected/admin/request/viewRequest";
	}

	@RequestMapping(value = "/rejectEmployRequest", method = RequestMethod.POST)
	public String rejectEmployRequest(Model model, @RequestParam int requestId) {
		logger.debug("Debug starts");
		TutorEmployRequest req = (TutorEmployRequest) rDao.getRequestById(requestId);
		rDao.deleteRequest(requestId);
		logger.info("Request" +requestId+ "Deleted");
		model.addAttribute("type",req.getType());
		model.addAttribute("reqList",rDao.getRequestByType(req.getType()));
		model.addAttribute("type", req.getType());
		model.addAttribute("reqList", rDao.getRequestByType(req.getType()));
		logger.debug("Debug ends");
		return "protected/admin/request/viewRequest";

	}
	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}

	public void settDao(TutorDAO tDao) {
		this.tDao = tDao;
	}

	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}

	public void setlDao(LessonDAO lDao) {
		this.lDao = lDao;
	}

}
