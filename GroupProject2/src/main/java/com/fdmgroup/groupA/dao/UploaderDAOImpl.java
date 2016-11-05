package com.fdmgroup.groupA.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.UploadFile;

public class UploaderDAOImpl implements UploaderDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void save(UploadFile uploadFile) {
		entityManager.persist(uploadFile);
	}
}
