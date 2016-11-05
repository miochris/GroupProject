package com.fdmgroup.groupA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.Student;

public class StudentDAOImpl extends UserDAOImpl implements StudentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	@Override
	public List<Student> listStudentByCourseId(int id) {
	TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.course.id =:id",Student.class);
	TypedQuery<Student> query1 = query.setParameter("id", id);
		return query1.getResultList() ;
	}
	
}
