package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.factory.UsersFactory;
import com.fdmgroup.groupA.model.Users;

public class RegisterControllerTest {

	private UserDAO userDao;
	private UsersFactory userFactory;
	private RegisterController rController;
	private Users users;
	private EntityManager entityManager;
	private Model model;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		model = mock(Model.class);
		userDao = mock(UserDAO.class);
		userFactory = mock(UsersFactory.class);
	}

	@Test
	public void testGoToRegistrationPage() {
		rController = new RegisterController();
		rController.setuDao(userDao);
		String view = rController.register(model);
		Assert.assertEquals("register", view);
	}

	@Test
	public void testRegisterFormPassesOnToSuccessPageAndStoresUser() {
		rController = new RegisterController();
		rController.setuDao(userDao);
		rController.setuFactory(userFactory);
		users = new Users("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "S");
		userDao.addUser(users);
		String view = rController.register(model, users);
		Assert.assertEquals("registerSuccess", view);
	}

}
