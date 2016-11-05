package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;

public class LogoutControllerTest {
	
	
	private LogoutController logoutController;
	private Model model;
	private HttpServletRequest request;
	private HttpSession session;
	
	@Before
	public void init(){
		model = mock(Model.class);
		request = mock(HttpServletRequest.class);
		logoutController = new LogoutController();
		request = new MockHttpServletRequest();
		session = mock(HttpSession.class);
	}

	@Test
	public void testLogOut(){
		String view = logoutController.Authenticate(model, request);
		Assert.assertEquals("redirect:index", view);
	}
}
