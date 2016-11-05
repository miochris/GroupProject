package com.fdmgroup.groupA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.Grade;

public class GradeDAOImpl implements GradeDAO {

	@PersistenceContext
	private EntityManager entityManager;
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	public GradeDAOImpl() {
		super();
	}


	@Override
	@Transactional
	public void updateGrade(Grade grade) {
		entityManager.merge(grade);
	}

	@Override
	@Transactional
	public void addNewGrade(Grade grade) {
		entityManager.persist(grade);

	}

	@Override
	public Grade findGradeByStudentAndLesson(String studentName, String lessonName) {
		TypedQuery<Grade> query = entityManager.createQuery(
				"select g from Grade g where g.student.name= :studentName and g.lesson.name=:lessonName", Grade.class);
		query.setParameter("studentName", studentName);
		query.setParameter("lessonName", lessonName);
		Grade result = query.getSingleResult();
		return result;
	}

	@Override
	public List<Grade> listAllGradesForEachLesson(int lessonId) {
		TypedQuery<Grade> query = entityManager.createQuery("select g from Grade g where g.lesson.id=:lessonId",
				Grade.class);
		query.setParameter("lessonId", lessonId);
		List<Grade> result = query.getResultList();
		return result;
	}

	@Override
	public Grade getGradeByGradeId(int id) {
		
		TypedQuery<Grade> query = entityManager.createQuery("select g from Grade g where g.id=:id",
				Grade.class);
		query.setParameter("id", id);
		Grade result = query.getSingleResult();
		return result;
	}

	@Override
	public List<Grade> findGradeIDByStudent(String studentName) {
		TypedQuery<Grade> query = entityManager.createQuery("select g from Grade g where g.student.username=:username",
				Grade.class);
		query.setParameter("username", studentName);
		List<Grade> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public void removeGrade(int gradeId) {
		Grade grade = entityManager.find(Grade.class, gradeId);
		entityManager.remove(grade);
	}


}
