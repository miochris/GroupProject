package com.fdmgroup.groupA.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;

public class UsersFactoryTest {

	private UsersFactory usersFactory;
	private Student student;
	private Tutor tutor;
	private Admin admin;

	@Before
	public void setup() {
		usersFactory = new UsersFactory();
		student = new Student("a", "b", "c", "d", "e", 0.0, "S");
		tutor = new Tutor("a", "b", "c", "d", "e", 0.0, "T");
		admin = new Admin("a", "b", "c", "d", "e", 0.0, "A");
	}

	@Test
	public void testCreateUserReturnNull() {
		usersFactory.createUser("a", "b", "c", "d", "e", 0.0, null);
		Assert.assertEquals(null, usersFactory.createUser("a", "b", "c", "d", "e", 0.0, null));
	}

	@Test
	public void testCreateUserReturnStudent() {
		usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S");
		Assert.assertEquals(student.getFirstname(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getFirstname());
		Assert.assertEquals(student.getLastname(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getLastname());
		Assert.assertEquals(student.getPassword(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getPassword());
		Assert.assertEquals(student.getUsername(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getUsername());
		Assert.assertEquals(student.getEmail(), usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getEmail());

	}

	@Test
	public void testCreateUserReturnTutor() {
		usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "T");
		Assert.assertEquals(tutor.getFirstname(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getFirstname());
		Assert.assertEquals(tutor.getLastname(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getLastname());
		Assert.assertEquals(tutor.getPassword(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getPassword());
		Assert.assertEquals(tutor.getUsername(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getUsername());
		Assert.assertEquals(tutor.getEmail(), usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getEmail());
	}

	@Test
	public void testCreateUserReturnAdmin() {
		usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "A");
		Assert.assertEquals(admin.getFirstname(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getFirstname());
		Assert.assertEquals(admin.getLastname(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getLastname());
		Assert.assertEquals(admin.getPassword(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getPassword());
		Assert.assertEquals(admin.getUsername(),
				usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getUsername());
		Assert.assertEquals(admin.getEmail(), usersFactory.createUser("a", "b", "c", "d", "e", 0.0, "S").getEmail());
	}
}
