package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.factory.RequestFactory;

import com.fdmgroup.groupA.logic.CourseLogic;
import com.fdmgroup.groupA.model.Course;

import com.fdmgroup.groupA.model.CourseAddRequest;
import com.fdmgroup.groupA.model.Request;

public class CourseControllerTest {

	private RequestDAO reqDao;
	private RequestFactory reqFactory;
	private CourseController cController;
	private EntityManager entityManager;
	private Model model;
	private HttpServletRequest request;
	private HttpSession session;
	private CourseLogic courseLogic;
	private CourseDAO cDao;


	@Before
	public void setup() {

		cDao = mock(CourseDAO.class);
		courseLogic = mock(CourseLogic.class);

		courseLogic = mock(CourseLogic.class);

		entityManager = mock(EntityManager.class);
		model = mock(Model.class);
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		reqDao = mock(RequestDAO.class);
		reqFactory = mock(RequestFactory.class);
		cController = new CourseController();
		cController.setcDao(cDao);
		cController.setrDao(reqDao);
		cController.setCourseLogic(courseLogic);
	}

	@Test
	public void testGoToCourseCreationPage() {
		String view = cController.goToCreateCourse(null, model, null);
		Assert.assertEquals("protected/tutor/lessons/createCourse", view);
	}

	
	@Test
	public void testCreateCourse(){
		Request req = reqFactory.createRequest("", "C", "", "");
		when(request.getSession()).thenReturn(session);
		String view = cController.CreateCourse(request, model, (CourseAddRequest) req);
		Assert.assertEquals("protected/tutor/lessons/createCourseRequestSent", view);
	}
	
	@Test
	public void testResponseRequest(){
		String startDate = "2016-9-28";
		String endDate = "2016-9-29";
		int requestId = 0;
		String submit = "Approve";
		double price = 100000.0;
		CourseAddRequest request = new CourseAddRequest("", "C", "Science","");
		//request.setId(0);
		when(reqDao.getRequestById(request.getId())).thenReturn(request);
		cController.setrDao(reqDao);
		String view = cController.ResponseReq(model, requestId, submit , startDate, endDate, price);
		Assert.assertEquals("protected/admin/request/viewRequest", view);
	}
	
	@Test
	public void testRejectCourse(){
		String startDate = "2016-9-28";
		String endDate = "2016-9-29";
		int requestId = 0;
		double price = 100000000.0;
		String submit = "Do Not Approve";
		cController.setrDao(reqDao);
		CourseAddRequest request = new CourseAddRequest("", "C", "Science","");
		when(reqDao.getRequestById(request.getId())).thenReturn(request);
		cController.setrDao(reqDao);
		String view = cController.ResponseReq(model, requestId, submit, startDate, endDate, price);
		Assert.assertEquals("protected/admin/request/viewRequest", view);
	}
	
	@Test
	public void testDeleteCourse(){
		cController.deleteCourse(model, 1);
		verify(cDao).deleteCourse(1);
		String view = cController.deleteCourse(model, 1);
		Assert.assertEquals("protected/admin/adminView", view);
	}
	
	@Test
	public void testGoToCreateCourse() {
		Assert.assertEquals("protected/admin/course/createCourse", cController.goToCreateCourse(model));	
	}
	
	@Test
	public void testCreateCourseWithoutReq(){
		cController.createCourseWithoutReq(model, "courseName", "startDate", "endDate", 0);
		verify(courseLogic).createCourse("courseName", "startDate", "endDate", 0);
		String view = cController.createCourseWithoutReq(model, "courseName", "startDate", "endDate", 0);
		Assert.assertEquals("protected/admin/course/createCourseSuccess", view);
	}
	
	@Test
	public void testChangePassword() {
		cController.changePassword(model, 0, 0);
		List<Course> courseList = new ArrayList<Course>();
		when(cDao.listCourses()).thenReturn(courseList);
		verify(courseLogic).changePrice(0, 0);
		verify(model).addAttribute("type","C");
		verify(model).addAttribute("coursesList",courseList);
		Assert.assertEquals("protected/admin/adminView", cController.changePassword(model, 0, 0));
	}
	
}
