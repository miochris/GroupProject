package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.CourseComparator;

public class HomeControllerTest {
	
	private HomeController homeController;
	private CourseDAO cDao;
	private CourseComparator cComparator;
	private Model model;
	
	@Before
	public void setup(){
		cDao = mock(CourseDAO.class);
		cComparator = mock(CourseComparator.class);
		model = mock(Model.class);
		homeController = new HomeController();
		homeController.setcDao(cDao);
		homeController.setcComparator(cComparator);
	}
	
	@Test
	public void testGoHome(){
		String view = homeController.goHome();
		Assert.assertEquals("index", view);
	}
	
	@Test
	public void testIndex(){
		List<Course> courseList = new ArrayList<Course>();
		when(cDao.listCourses()).thenReturn(courseList);
		courseList.sort(cComparator);
		String view = homeController.index(model);
		Assert.assertEquals("index", view);
	}
	
	@Test
	public void testAboutUs(){
		String view = homeController.aboutUs();
		Assert.assertEquals("about", view);
	}
	
	@Test
	public void testContactUs(){
		String view = homeController.contactUs();
		Assert.assertEquals("ContactUs", view);
	}
	
}
