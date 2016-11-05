package com.fdmgroup.groupA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.Request;

public class RequestDAOImpl implements RequestDAO{

	@PersistenceContext
	private EntityManager entityManager;

	public RequestDAOImpl() {
		super();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	@Override
	public void addRequest(Request request) {
		entityManager.persist(request);
	}

	@Transactional
	@Override
	public void deleteRequest(int id) {
		Request request = entityManager.find(Request.class, id);
		entityManager.remove(request);
	}

	@Transactional
	@Override
	public void updateRequest(Request request) {
		entityManager.merge(request);
	}

	@Transactional
	@Override
	public List<Request> getRequestByUserName(String username) {
		Query query = entityManager.createQuery("SELECT r FROM Request r WHERE r.username = :username",Request.class);
		query.setParameter("username", username);
		List<Request> reqList = query.getResultList();
		return reqList;
	}

	@Transactional
	@Override
	public List<Request> getAllRequest() {
		TypedQuery<Request> query = entityManager.createQuery("Select r from Request r", Request.class);
		List<Request> reqList = query.getResultList();
		return reqList;
	}

	@Transactional
	@Override
	public Request getRequestById(int id) {
		Request req = entityManager.find(Request.class, id);
		return req;
	}

	@Override
	public List<Request> getRequestByType(String type) {
		TypedQuery<Request> query = entityManager.createQuery("SELECT r FROM Request r WHERE r.type = :type",Request.class);
		query.setParameter("type", type);
		List<Request> reqList = query.getResultList();
		return reqList;
	}
	
}
