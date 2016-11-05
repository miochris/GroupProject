package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.logic.AdminLogic;
import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Request;
import com.fdmgroup.groupA.model.Users;

public class AdminControllerTest {

	private Admin admin;
	private RequestDAO rDao;
	private UserDAO uDao;
	private CourseDAO cDao;
	private LessonDAO lDao;
	private AdminLogic adminLogic;
	private AdminController aController;
	private Model model;
	private HttpServletRequest request;
	private HttpSession session;
	
	@Before
	public void setup() {
		
		admin = mock(Admin.class);
		session = mock(HttpSession.class);
		aController = new AdminController();
		model = mock(Model.class);
		rDao = mock(RequestDAO.class);
		uDao = mock(UserDAO.class);
		cDao = mock(CourseDAO.class);
		lDao = mock(LessonDAO.class);
		adminLogic = mock(AdminLogic.class);
		aController.setcDao(cDao);
		aController.setlDao(lDao);
		aController.setrDao(rDao);
		aController.setuDao(uDao);
		aController.setAdminLogic(adminLogic);
		request = mock(HttpServletRequest.class);
	}
	
	@Test
	public void testPersistAdmin() {
		
		Assert.assertEquals("about", aController.persistAdmin(model));
	}
	
	@Test
	public void goToIndexIfUserIsNull() {
		
		Assert.assertEquals("index", aController.goToAdminIndex(model, request));
		Assert.assertEquals("index", aController.goToViewRequest(model,"type",request));
		Assert.assertEquals("index", aController.goToView(model, "type", request));
		
	}
	
	@Test
	public void goToAdminIndexIfUserIsNotNull() {
		
		Users user = new Users();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		when(uDao.getUserByUserName("admin")).thenReturn(admin);
		String view = aController.goToAdminIndex(model, request);
		Assert.assertEquals("protected/admin/adminIndex", view);
		
	}
	
	@Test
	public void goToViewRequestIfUserIsNotNull() {
		
		Users user = new Users();
		List<Request> reqList = mock(List.class);
		List<Course> courseList = mock(List.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		when(rDao.getRequestByType("type")).thenReturn(reqList);
		when(cDao.listCourses()).thenReturn(courseList);
		String string = aController.goToViewRequest(model, "type", request);
		Assert.assertEquals("protected/admin/request/viewRequest",string);
	}
	
	@Test
	public void goToViewIfUserIsNotNull() {
		Users user = new Users();
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		Map<String, String> map = mock(Map.class);
		when(adminLogic.createTutorCVMap()).thenReturn(map);
		Assert.assertEquals("protected/admin/adminView",aController.goToView(model, "S", request));
		Assert.assertEquals("protected/admin/adminView",aController.goToView(model, "T", request));
		Assert.assertEquals("protected/admin/adminView",aController.goToView(model, "C", request));
		Assert.assertEquals("protected/admin/adminView",aController.goToView(model, "L", request));
		Assert.assertEquals("protected/admin/adminView",aController.goToView(model, "type", request));
	}
	
	@Test
	public void testChangePassword() {
		
		Assert.assertEquals("protected/admin/adminView",aController.changePassword(model, "S", "username", "newPassword"));
		Assert.assertEquals("protected/admin/adminView",aController.changePassword(model, "T", "username", "newPassword"));
		Assert.assertEquals("protected/admin/adminView",aController.changePassword(model, "type", "username", "newPassword"));
		verify(model).addAttribute("type","S");
		verify(model).addAttribute("type","T");	
	}
	
	@Test
	public void testAdminQuitsStudentsFromCourse() {
		List<Users> studentList = new ArrayList<Users>();
		when(uDao.getUserByType("S")).thenReturn(studentList);
		aController.adminQuitsStudentsFromCourse(model, "username");
		verify(adminLogic).quitCourse("username");
		verify(model).addAttribute("type","S");
		verify(model).addAttribute("studentsList",studentList);
		Assert.assertEquals("protected/admin/adminView", aController.adminQuitsStudentsFromCourse(model, "username"));
		
	}
	
}
