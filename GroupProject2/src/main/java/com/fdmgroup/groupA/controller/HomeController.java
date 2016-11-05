package com.fdmgroup.groupA.controller;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.CourseComparator;

@Controller
public class HomeController {
	
	@Autowired
	private CourseDAO cDao;
	private CourseComparator cComparator;
	@Autowired
	 Logger logger ;
	
	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}
	
	public void setcComparator(CourseComparator cComparator) {
		this.cComparator = cComparator;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goHome() {
		return "index";
	}

	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model) {
		List<Course> courseList = cDao.listCourses();
		cComparator = new CourseComparator();
		courseList.sort(cComparator);
		model.addAttribute("coursesList", courseList);
		return"index";
	}

	
	@RequestMapping(value="aboutUs", method=RequestMethod.GET)
	public String aboutUs(){
		return "about";
	}
	@RequestMapping(value="contactUs", method=RequestMethod.GET)
	public String contactUs(){
		return "ContactUs";
	}
	
}
