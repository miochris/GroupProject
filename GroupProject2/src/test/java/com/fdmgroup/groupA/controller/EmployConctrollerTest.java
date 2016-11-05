package com.fdmgroup.groupA.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Request;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.TutorEmployRequest;

public class EmployConctrollerTest {
	private Model model;
	private EmployController eController;
	private RequestDAO rDao;
	private TutorDAO tDao;
	private CourseDAO cDao;
	private LessonDAO lDao;
	
	
	
	@Before
	public void setup(){
		model=mock(Model.class);
		eController =new EmployController();
		rDao=mock(RequestDAO.class);
		tDao=mock(TutorDAO.class);
		cDao=mock(CourseDAO.class);
		lDao = mock(LessonDAO.class);
		eController.setcDao(cDao);
		eController.setlDao(lDao);
		eController.setrDao(rDao);
		eController.settDao(tDao);
		
	}
	
	@Test
	public void testGoToApplication(){
		TutorEmployRequest request = new TutorEmployRequest("","T","","");
		String result = eController.goToApplication(model);
		assertEquals("tutor/account/employRequest", result);
	}
	@Test
	public void testApplicationSuccess(){
		TutorEmployRequest request = new TutorEmployRequest("","T","","");
		String result = eController.ApplicationSuccess(model, request);
		verify(model).addAttribute("req", request);
		assertEquals("tutor/account/RequestSent", result);
	}
	
	@Test
	public void testRejectEmployRequest(){
		int requestId = 0;
		TutorEmployRequest request = new TutorEmployRequest("","T","","");
		when(rDao.getRequestById(request.getId())).thenReturn(request);
		eController.setrDao(rDao);
		String result = eController.rejectEmployRequest(model, requestId);
		assertEquals("protected/admin/request/viewRequest", result);
	}
	
	@Test
	public void testApproveEmploymentRequest() {
		TutorEmployRequest request = mock(TutorEmployRequest.class);
		Tutor tutor = mock(Tutor.class);
		Course course = mock(Course.class);
		List<Tutor> tutorList = new ArrayList<Tutor>();
		when(rDao.getRequestById(0)).thenReturn(request);
		when(request.getUsername()).thenReturn("username");
		when(tDao.getUserByUserName("username")).thenReturn(tutor);
		when(cDao.getCourseByCourseName("courseName")).thenReturn(course);
		when(request.getType()).thenReturn("type");
		eController.ApproveEmployRequest(model, 0, "courseName");
		verify(tutor).setHired(true);
		verify(tutor).setCourse(course);
		verify(cDao).updateCourse(course);
		verify(tDao).updateUser(tutor);
		verify(model).addAttribute("type","type");
		verify(rDao).deleteRequest(0);
		List<Request> reqList = new ArrayList<Request>();
		when(rDao.getRequestByType("type")).thenReturn(reqList);
		verify(model).addAttribute("reqList",reqList);
		List<Course> coursesList = new ArrayList<Course>();
		when(cDao.listCourses()).thenReturn(coursesList);
		verify(model).addAttribute("coursesList",coursesList);
		
		assertEquals("protected/admin/request/viewRequest", eController.ApproveEmployRequest(model, 0, "courseName"));
	}
	
}
