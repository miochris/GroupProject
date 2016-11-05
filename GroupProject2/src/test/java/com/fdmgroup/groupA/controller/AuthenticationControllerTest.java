package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.*;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Users;

import org.junit.Assert;

public class AuthenticationControllerTest {
	
	private AuthenticationController authenticationController;
	private Model mockModel;
	private HttpServletRequest req;
	private HttpSession mockSession;
	private Principal mockPrincipal;
	private UserDAO uDao;
	private Users student;
	private Logger logger;
	
	@Before
	public void setup(){
		uDao = mock(UserDAO.class);
		authenticationController = new AuthenticationController();
		mockModel = mock(Model.class);
		req = mock(HttpServletRequest.class);
		mockSession = mock(HttpSession.class);
		mockPrincipal = mock(Principal.class);
		authenticationController.setuDao(uDao);
		
		
	}
	
	@Test
	public void testAuthenticateStudent(){
		//Arrange
		when(req.getSession()).thenReturn(mockSession);
		when(req.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(anyString());
		when(req.isUserInRole("S")).thenReturn(true);
		
		//Act
		String result = authenticationController.authenticate(mockModel, req);
		//Assert
		Assert.assertEquals("index", result);
	}
	
	@Test
	public void testAuthenticateTutor(){
		//Arrange
		when(req.getSession()).thenReturn(mockSession);
		when(req.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(anyString());
		when(req.isUserInRole("S")).thenReturn(false);
		when(req.isUserInRole("T")).thenReturn(true);
		//Act
		String result = authenticationController.authenticate(mockModel, req);
		//Assert
		Assert.assertEquals("index", result);
	}
	
	@Test
	public void testAuthenticateAdmin(){
		//Arrange
		when(req.getSession()).thenReturn(mockSession);
		when(req.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(anyString());
		when(req.isUserInRole("S")).thenReturn(false);
		when(req.isUserInRole("T")).thenReturn(false);
		when(req.isUserInRole("A")).thenReturn(true);
		//Act
		String result = authenticationController.authenticate(mockModel, req);
		//Assert
		Assert.assertEquals("index", result);
	}
	
	@Test
	public void testAuthenticateFail(){
		//Arrange
		when(req.getSession()).thenReturn(mockSession);
		when(req.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(anyString());
		when(req.isUserInRole("S")).thenReturn(false);
		when(req.isUserInRole("T")).thenReturn(false);
		when(req.isUserInRole("A")).thenReturn(false);
		//Act
		String result = authenticationController.authenticate(mockModel, req);
		//Assert
		Assert.assertEquals(null, result);
	}
}
