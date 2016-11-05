package com.fdmgroup.groupA.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.LessonAddRequest;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;

@Controller
public class LessonController {

	@Autowired
	RequestDAO rDao;
	@Autowired
	LessonDAO lDao;
	@Autowired
	CourseDAO cDao;
	@Autowired
	TutorDAO tDao;
	
	Tutor tutor;
	Lesson lesson;

	@RequestMapping(value = "/createLesson", method = RequestMethod.GET)
	public String goToCreateLesson(HttpServletRequest request,Model model, LessonAddRequest lessonAddRequest) {
		model.addAttribute("request", new LessonAddRequest());
		return "protected/tutor/lessons/createLesson";
	}

	@RequestMapping(value = "/createLesson", method = RequestMethod.POST)
	public String CreateLesson(HttpServletRequest request,Model model, LessonAddRequest lessonAddRequest) {

		rDao.addRequest(lessonAddRequest);
		return "protected/tutor/lessons/createLessonRequestSent";
	}

	@RequestMapping(value = "/deleteLesson", method = RequestMethod.POST)
	public String deleteLesson(Model model, @RequestParam String lessonName) {

		Lesson lesson = lDao.getLessonByLessonName(lessonName);
		lDao.removeLesson(lesson.getId());

		return "protected/admin/adminView";
	}

	@RequestMapping(value = "/assignTutorToLesson", method = RequestMethod.POST)
	public String assignTutorToLesson(Model model, @RequestParam String tutorUsername,
			@RequestParam String lessonName) {

		Tutor tutor = (Tutor) tDao.getUserByUserName(tutorUsername);
		Lesson lesson = lDao.getLessonByLessonName(lessonName);
		lesson.setTutor(tutor);
		tutor.getLessonList().add(lesson);
		tDao.updateUser(tutor);
		lDao.updateLesson(lesson);

		return "";
	}
	
	@RequestMapping(value = "/tutorQuitLesson", method = RequestMethod.POST)
	public String tutorQuitLesson(HttpServletRequest request) {
		String user = (String) request.getAttribute("user");
		Tutor tutor = (Tutor) tDao.getUserByUserName(user);
		String lessonId = request.getParameter("lessonId");
		Lesson lesson = lDao.getLesson(Integer.parseInt(lessonId));
		
		request.getSession().setAttribute("lesson", lesson);
		request.getSession().setAttribute("tutor", tutor);

		return "protected/admin/request/tutorQuitLesson";
	}
	

	@RequestMapping(value = "/approveCreateLesson", method = RequestMethod.POST)
	public String ApproveReq(Model model, @RequestParam int requestId) {

		LessonAddRequest req = (LessonAddRequest) rDao.getRequestById(requestId);
		Tutor tutor = (Tutor) tDao.getUserByUserName(req.getUsername());
		Lesson lesson = new Lesson(tutor, req.getLessonName());
		lDao.addLesson(lesson);
		rDao.deleteRequest(requestId);
		model.addAttribute("reqList", rDao.getRequestByType("L"));

		return "protected/admin/request/viewRequest";
	}

	@RequestMapping(value = "/rejectCreateLesson", method = RequestMethod.POST)
	public String RejectReq(Model model, @RequestParam int id) {
		rDao.deleteRequest(id);

		return "protected/admin/request/viewRequest";
	}

	@RequestMapping(value = "/displayLessons", method = RequestMethod.POST)
	public String displayLessons(Model model, @RequestParam String username) {

		Tutor tutor = (Tutor) tDao.getUserByUserName(username);
		model.addAttribute("lessonList", tutor.getLessonList());

		return "protected/tutor/tutorLesson";
	}
	
	@RequestMapping(value="/createLessonWithoutReq", method=RequestMethod.GET)
	public String goToCreateLessonWithoutReq(Model model) {
		
		model.addAttribute("lesson",new Lesson());
		model.addAttribute("coursesList", cDao.listCourses());
		model.addAttribute("tutorsList", null);
		return "protected/admin/lesson/createLesson";
		
	}
	
	@RequestMapping(value="/createLessonWithoutReq", method=RequestMethod.POST)
	public String createLessonWithoutReq(Model model, Lesson lesson, @RequestParam String tutorUsername) {
		
		tutor = (Tutor)tDao.getUserByUserName(tutorUsername);
		lesson.setTutor(tutor);
		lDao.addLesson(lesson);
		return"protected/admin/lesson/createLessonSuccess";
	}
	
	@RequestMapping(value="/changeLessonName", method=RequestMethod.POST)
	public String changeLessonName(Model model, @RequestParam String lessonName, @RequestParam int lessonId) {
		
		lesson = lDao.getLesson(lessonId);
		lesson.setName(lessonName);
		lDao.updateLesson(lesson);
		model.addAttribute("lessonsList",lDao.listLessons());
		
		return"protected/admin/adminView";
	}	

	
	
	
	
	
	
	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}

	public void setlDao(LessonDAO lDao) {
		this.lDao = lDao;
	}

	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}

	public void setuDao(TutorDAO tDao) {
		this.tDao = tDao;
	}
}
