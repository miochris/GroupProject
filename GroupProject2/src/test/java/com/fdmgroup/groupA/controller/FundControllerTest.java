package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.factory.UsersFactory;
import com.fdmgroup.groupA.model.Users;

public class FundControllerTest {

	private StudentDAO sDao;
	private UsersFactory userFactory;
	private FundController fundController;
	private Users users;
	private EntityManager entityManager;
	private Model model;
	private UserDAO uDao;

	@Before
	public void setup() {
		model = mock(Model.class);
		sDao = mock(StudentDAO.class);
		uDao = mock(UserDAO.class);
		fundController = new FundController();
		fundController.setuDao(uDao);
		users = new Users("stringfirstname", "Stringlastname", "Stringemail", "Stringuername", "Stringpassword", 10100010.0,
				"Stringrole");
	}

	@Test
	public void testDeposit() {
		String username = "sun";
		when(uDao.getUserByUserName(username)).thenReturn(users);
		String dep = fundController.deposit(model, username, 2.1);
		Assert.assertEquals("/protected/student/account/balance", dep);
		verify(uDao).updateUser(users);
	}

	@Test
	public void testPay(){
		String username = "sun";
		double pay = 2.2;
		when(uDao.getUserByUserName(username)).thenReturn(users); 
		String dep = fundController.pay(model, username, pay);
		Assert.assertEquals("", dep);
	}
	
	@Test
	public void testWithdraw(){
		String username = "sun";
		Double withdraw = 2.2;
		when(uDao.getUserByUserName(username)).thenReturn(users);
		String dep = fundController.withdraw(model, username, withdraw);
		Assert.assertEquals("", dep);
	}
}
