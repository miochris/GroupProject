package com.fdmgroup.groupA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.Users;

public class UserDAOImpl implements UserDAO {
	
	final static Logger log = Logger.getLogger(UserDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void addUser(Users user) {
		entityManager.persist(user);

	}

	@Transactional
	@Override
	public void deleteUserByUsername(String username) {
		Users user = entityManager.find(Users.class, username);
		entityManager.remove(user);
	}

	@Transactional
	@Override
	public void updateUser(Users user) {
		entityManager.merge(user);
	}


	@Override
	@Transactional
	public Users getUserByUserName(String username) {
		
		return entityManager.find(Users.class, username);
		
	}

	@Override
	@Transactional
	public List<Users> getUserByType(String role) {
		
		TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.role = :role",Users.class);
		query.setParameter("role", role);
		List<Users> userList = query.getResultList();
		return userList;
		
	}
	
	@Override
	@Transactional
	public List<Users> getAllUser() {
		TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM User u", Users.class);
		return query.getResultList();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
