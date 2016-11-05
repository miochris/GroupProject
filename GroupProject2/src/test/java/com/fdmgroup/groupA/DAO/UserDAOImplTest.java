package com.fdmgroup.groupA.DAO;

import static org.mockito.Mockito.*;
import org.junit.Assert;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fdmgroup.groupA.dao.UserDAOImpl;
import com.fdmgroup.groupA.model.Users;

public class UserDAOImplTest {

	private EntityManager mockEM;
	private UserDAOImpl userDAO;
	private Users mockUser;
	private TypedQuery<Users> mockQuery;

	@Before
	public void setup() {
		mockEM = Mockito.mock(EntityManager.class);
		mockUser = Mockito.mock(Users.class);
		mockQuery = Mockito.mock(TypedQuery.class);
		userDAO = new UserDAOImpl();
		userDAO.setEntityManager(mockEM);
	}

	@Test
	public void testAdduser() {
		userDAO.addUser(mockUser);
		verify(mockEM).persist(mockUser);
	}

	@Test
	public void testDeleteUserByUsername() {
		when(mockEM.find(Users.class, " ")).thenReturn(mockUser);
		userDAO.deleteUserByUsername(" ");
		verify(mockEM).remove(mockUser);
	}

	@Test
	public void testUpdateUser() {
		userDAO.updateUser(mockUser);
		verify(mockEM).merge(mockUser);

	}
	
	@Test
	public void testGetListOfUser(){
		List<Users> mockList= mock(List.class);
		when(mockEM.createQuery("SELECT u FROM User u", Users.class )).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		userDAO.getAllUser();
		Assert.assertEquals(mockList, userDAO.getAllUser());
	}

	@Test
	public void getUserByUsername(){
		Users user = new Users("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "S");
		when(mockEM.find(Users.class, user.getUsername())).thenReturn(user);
		Users result = userDAO.getUserByUserName(user.getUsername());
		Assert.assertEquals(user, result);
	}

	@Test
	public void testGetUserBytype(){
		List<Users> list = mock(List.class);
		Users user = new Users("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "S");
		when(mockEM.createQuery("SELECT u FROM Users u WHERE u.role = :role",Users.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(list);
		List<Users> result = userDAO.getUserByType("S");
		Assert.assertEquals(list, result);
	}
}
