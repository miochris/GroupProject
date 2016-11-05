package com.fdmgroup.groupA.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.logic.AdminLogic;
import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Users;

@Controller
public class AdminController {

	@Autowired
	Admin admin;
	@Autowired
	RequestDAO rDao;
	@Autowired
	@Qualifier("uDao")
	UserDAO uDao;
	@Autowired
	CourseDAO cDao;
	@Autowired
	LessonDAO lDao;
	@Autowired
	AdminLogic adminLogic;
	private Map<String,String> TutorCVMap;
	
	@Autowired
	 Logger logger  ;
	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}

	public void setlDao(LessonDAO lDao) {
		this.lDao = lDao;
	}

	public void setAdminLogic(AdminLogic adminLogic) {
		this.adminLogic = adminLogic;
	}

	@RequestMapping(value = "/addAdmin", method = RequestMethod.GET )
	public String persistAdmin(Model model){
		uDao.addUser(admin);
		return "about";
	}
	
	
	@RequestMapping(value = "/adminIndex", method = RequestMethod.GET)
	public String goToAdminIndex(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users user = null;
		try {
			user = (Users) session.getAttribute("user");

		} catch (Exception e) {

		}
		if (user == null) {
			return "index";
		} else {
			model.addAttribute("coursesList", cDao.listCourses());
			model.addAttribute("studentsList", uDao.getUserByType("S"));
			model.addAttribute("tutorsList", uDao.getUserByType("T"));
			model.addAttribute("lessonsList", lDao.listLessons());
			model.addAttribute("tutorRequest",rDao.getRequestByType("T"));
			model.addAttribute("studentRequest",rDao.getRequestByType("S"));
			model.addAttribute("courseRequest",rDao.getRequestByType("C"));
			model.addAttribute("lessonRequest",rDao.getRequestByType("L"));
			model.addAttribute("admin",uDao.getUserByUserName("admin"));

			return "protected/admin/adminIndex";
		}
		
	}

	@RequestMapping(value = "/adminViewRequest", method = RequestMethod.GET)
	public String goToViewRequest(Model model, @RequestParam String type, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users user = null;
		try {
			user = (Users) session.getAttribute("user");

		} catch (Exception e) {

		}
		if (user == null) {
			return "index";
		} else {
			model.addAttribute("tutorRequest",rDao.getRequestByType("T"));
			model.addAttribute("studentRequest",rDao.getRequestByType("S"));
			model.addAttribute("courseRequest",rDao.getRequestByType("C"));
			model.addAttribute("lessonRequest",rDao.getRequestByType("L"));
			model.addAttribute("reqList", rDao.getRequestByType(type));
			model.addAttribute("type", type);
			model.addAttribute("coursesList", cDao.listCourses());
			return "protected/admin/request/viewRequest";
		}
	}

	@RequestMapping(value = "/adminView", method = RequestMethod.GET)
	public String goToView(Model model, @RequestParam String type, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users user = null;
		try {
			user = (Users) session.getAttribute("user");

		} catch (Exception e) {

		}
		if (user == null) {
			return "index";
		} else {
			model.addAttribute("type", type);
			if (type.equals("S")) {
				model.addAttribute("studentsList", uDao.getUserByType("S"));
			} else if (type.equals("T")) {
				TutorCVMap = adminLogic.createTutorCVMap();
				model.addAttribute("tutorsList", uDao.getUserByType("T"));
				model.addAttribute("TutorCVMap",TutorCVMap);
			} else if (type.equals("C")) {
				model.addAttribute("courseLessonMap", adminLogic.createCourseLessonMap());
				model.addAttribute("coursesList", cDao.listCourses());
			} else if (type.equals("L")) {
				model.addAttribute("lessonsList", lDao.listLessons());
			}
			return "protected/admin/adminView";
		}
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String changePassword(Model model, @RequestParam String type, @RequestParam String username, @RequestParam String newPassword) {
		adminLogic.changePassword(username, newPassword);
		model.addAttribute("type",type);
		if(type.equals("T")) {
			
			model.addAttribute("tutorsList",uDao.getUserByType(type));
		} else if(type.equals("S")) {
			model.addAttribute("studentsList",uDao.getUserByType(type));
		}
		return "protected/admin/adminView";
	}
	
	@RequestMapping(value="/adminQuitStudentFromCourse", method=RequestMethod.POST)
	public String adminQuitsStudentsFromCourse(Model model, @RequestParam String username) {
		
		adminLogic.quitCourse(username);
		model.addAttribute("type","S");
		model.addAttribute("studentsList",uDao.getUserByType("S"));
		return "protected/admin/adminView";
		
	}
	
}
