package com.fdmgroup.groupA.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fdmgroup.groupA.model.Tutor;

public class TutorDAOImpl extends UserDAOImpl implements TutorDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Tutor findTutorByLesson(String lessonName) {
		TypedQuery<Tutor> query = entityManager.createQuery("Select t from tutor t where t.course.name =:lessonName",
				Tutor.class);
		query.setParameter("lessonName", lessonName);
		Tutor tutorByLesson = query.getSingleResult();

		return tutorByLesson;
	}
	
	@Override
	public double getPercentageByTutorName(String username) {
		TypedQuery<Tutor> query = entityManager.createQuery("Select t from tutor t where t.username =:username",
				Tutor.class);
		TypedQuery<Tutor>  query1= query.setParameter("username", username);
		Tutor tutor = query.getSingleResult();
		return tutor.getBalance();
	}

}
