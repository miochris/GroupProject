package com.fdmgroup.groupA.controllerBean;

import org.junit.Before;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.logic.FundManage;
import com.fdmgroup.groupA.model.Users;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

import org.junit.Test;

public class FundManageControllerTest {
	UserDAO uDao;
	FundManage fundManage;
	Users user;
	Users user2;
	HttpServletRequest request;
	FundManageController fc;
	double withdraw = 12.3;

	@Before
	public void init() {
		uDao = mock(UserDAO.class);
		fundManage = mock(FundManage.class);
		user = mock(Users.class);
		user2 = mock(Users.class);
		request = mock(HttpServletRequest.class);

		fc = new FundManageController();
		fc.setuDao(uDao);
		fc.setUser(user);
		fc.setUser2(user2);
		fc.setFundManage(fundManage);
	}

	@Test
	public void testWIthdraw() {

		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when((Users) request.getSession().getAttribute("user")).thenReturn(user);
		when((Users) request.getSession().getAttribute("user")).thenReturn(user2);
		String result = fc.withdraw(request, withdraw);
		assertEquals("redirect:/tutorAccount", result);
		verify(fundManage).withdraw(user.getUsername(), withdraw);
		
	}

}
