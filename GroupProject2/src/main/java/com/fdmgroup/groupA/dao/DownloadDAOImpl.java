package com.fdmgroup.groupA.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.UploadFile;

public class DownloadDAOImpl implements DownloadDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public String getFilePathByUserNameAndType(String username, int type) {

		TypedQuery<UploadFile> query = entityManager.createQuery("SELECT f FROM UploadFile f WHERE  f.username = :username AND f.type = :type", UploadFile.class);
		TypedQuery<UploadFile> query1 = query.setParameter("username", username);
		TypedQuery<UploadFile> query2 = query1.setParameter("type", type);
		try{
		String filePath = query2.getSingleResult().getFilePath();
		return filePath;
		} catch(Exception e) {
		return "0";
		}

	}
}
