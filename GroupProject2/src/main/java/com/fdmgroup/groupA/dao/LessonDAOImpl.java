package com.fdmgroup.groupA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.Lesson;

public class LessonDAOImpl implements LessonDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public LessonDAOImpl(){
		super();
	}
	
	@Override
	@Transactional
	public Lesson getLesson(int lessonID) {
		return entityManager.find(Lesson.class, lessonID);
	}

	@Override
	@Transactional
	public void addLesson(Lesson lesson) {
		entityManager.persist(lesson);
	}

	@Override
	@Transactional
	public void updateLesson(Lesson lesson) {
		entityManager.merge(lesson);
	}

	@Override
	@Transactional
	public void removeLesson(int lessonID) {
		Lesson lesson = entityManager.find(Lesson.class, lessonID);
		entityManager.remove(lesson);
	}
	
	@Override
	@Transactional
	public List<Lesson> listLessons() {
		TypedQuery<Lesson> q = entityManager.createQuery("Select l from Lesson l", Lesson.class);
		List<Lesson> lessonList = q.getResultList();
		return lessonList;
	}

	@Override
	@Transactional
	public Lesson getLessonByLessonName(String lessonName) {
		TypedQuery<Lesson> q = entityManager.createQuery("Select * from Lesson where name=?", Lesson.class);
		TypedQuery<Lesson> x = q.setParameter(1, lessonName);
		Lesson lesson = x.getSingleResult();
		return lesson;
	}
}
