package com.fdmgroup.groupA.logic;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Users;

public class FundManageTest {

	private UserDAO uDao;
	private FundManage fundManage = new FundManage();
	
	@Before
	public void setup() {
		uDao = mock(UserDAO.class);
		fundManage.setuDao(uDao);
		
	}
	
	@Test
	public void testDeposit() {
		
		Users user = mock(Users.class);
		when(uDao.getUserByUserName("username")).thenReturn(user);
		fundManage.deposit("username", 0);
		verify(user).deposit(0);
		verify(uDao).updateUser(user);
	}
	
	@Test
	public void testPay() {
		Users user = mock(Users.class);
		when(uDao.getUserByUserName("username")).thenReturn(user);
		fundManage.pay("username", 0);
		verify(user).pay(0);
		verify(uDao).updateUser(user);
	}
	@Test
	public void testWithdraw() {
		Users user = mock(Users.class);
		when(uDao.getUserByUserName("username")).thenReturn(user);
		fundManage.withdraw("username", 0);
		verify(user).deposit(0);
		verify(uDao).updateUser(user);
	}
}
