package com.fdmgroup.groupA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.logic.AdminLogic;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.TutorEmployRequest;
import com.fdmgroup.groupA.model.Users;

@Controller
public class TutorController {

	@Autowired
	TutorDAO tDao;

	@Autowired
	CourseDAO cDao;



	@Autowired
	RequestDAO rDao;

	@Autowired
	StudentDAO sDao;
	@Autowired
	AdminLogic adminLogic;
	
	Users user;
	Course course;
	String courseId;
	String lessonId ;
	Tutor tutor;

	@Autowired
	 Logger logger ;
	



	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	List<Student> studentList;
	


	public void setUser(Users user) {
		this.user = user;
	}

	List<Course> courseList ;
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void settDao(TutorDAO tDao) {
		this.tDao = tDao;
	}

	@RequestMapping(value = "/tutorHome", method = RequestMethod.GET)
	public String goToTutorHomeFromIndex() {

		return "protected/tutor/index";

	}

	@RequestMapping(value = "/tutorAccount", method = RequestMethod.GET)
	public String goToTutorAccount(HttpServletRequest req, Model model) {

		 courseList = cDao.listCourses();
		req.getSession().setAttribute("courseList", courseList);

		model.addAttribute("tutorEmployRequest", new TutorEmployRequest());
		model.addAttribute("TutorCVMap", adminLogic.createTutorCVMap());
		return "/protected/tutor/account/accountHome";
	}

	@RequestMapping(value = "/lesson", method = RequestMethod.GET)
	public String goToTutorLessons() {
		return "protected/tutor/lessons/lesson";
	}

	@RequestMapping(value = "/applyForNewCourse", method = RequestMethod.POST)
	public String chooseCourseRequestSent(HttpServletRequest request, Model model,
			TutorEmployRequest tutorEmployRequest) {
		 user = (Users) request.getSession().getAttribute("user");

		tutorEmployRequest.setUsername(user.getUsername());
		rDao.addRequest(tutorEmployRequest);

		return "protected/tutor/lessons/newCourseRequestSent";
	}

	@RequestMapping(value = "/deleteTutor", method = RequestMethod.POST)
	public String deleteTutor(Model model, @RequestParam String username) {
		tDao.deleteUserByUsername(username);
		model.addAttribute("type", "T");
		model.addAttribute("tutorsList", tDao.getUserByType("T"));
		model.addAttribute("TutorCVMap", adminLogic.createTutorCVMap());
		return "protected/admin/adminView";
	}

	@RequestMapping(value = "/listTutors", method = RequestMethod.POST)
	public String listTutors(Model model, @RequestParam String courseName) {

		 course = cDao.getCourseByCourseName(courseName);
		model.addAttribute("tutorsList", course.getTutorList());
		model.addAttribute("coursesList", cDao.listCourses());
		model.addAttribute("courseName", course.getCourseName());
		model.addAttribute("lesson", new Lesson());

		return "protected/admin/lesson/createLesson";

	}

	@RequestMapping(value = "/viewStudentsByCourseId", method = RequestMethod.GET)
	public String displayStudentsByCourseId(HttpServletRequest request) {
		 courseId = request.getParameter("courseId");
		 lessonId = request.getParameter("lessonId");
		 studentList = sDao.listStudentByCourseId(Integer.parseInt(courseId));
		request.getSession().setAttribute("studentList", studentList);
		return "protected/tutor/lessons/viewStudents";
	}

	@RequestMapping(value = "/changePer", method = RequestMethod.POST)
	public String changeTutorPercentage(Model model, @RequestParam String username, @RequestParam double percentage) {

		 tutor = (Tutor) tDao.getUserByUserName(username);
		tutor.setPercentage(percentage);
		tDao.updateUser(tutor);
		model.addAttribute("type", "T");
		model.addAttribute("tutorsList", tDao.getUserByType("T"));
		return "protected/admin/adminView";

	}


	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}



	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}



	public void setsDao(StudentDAO sDao) {
		this.sDao = sDao;
	}



	public void setAdminLogic(AdminLogic adminLogic) {
		this.adminLogic = adminLogic;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}



	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}



	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
}
