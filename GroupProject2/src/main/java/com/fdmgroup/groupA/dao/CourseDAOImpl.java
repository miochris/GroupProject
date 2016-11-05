package com.fdmgroup.groupA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;

public class CourseDAOImpl implements CourseDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public CourseDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public Course getCourse(int id) {
		return entityManager.find(Course.class, id);
	}

	@Override
	@Transactional
	public Course getCourseByCourseName(String courseName) {
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.courseName = :name",Course.class);
		TypedQuery<Course> query2 = query.setParameter("name", courseName);
		Course course = query2.getSingleResult();
		return course;
		
	}
	
	@Override
	@Transactional
	public void addCourse(Course course) {
		entityManager.persist(course);
	}

	@Override
	@Transactional
	public void updateCourse(Course course) {
		entityManager.merge(course);
	}

	@Override
	@Transactional
	public void deleteCourse(int id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}
	
	@Override
	@Transactional
	public List<Course> listCourses() {
		TypedQuery<Course> c = entityManager.createQuery("Select c from Course c", Course.class);
		List<Course> courseList = c.getResultList();
		return courseList;
	}

	@Override
	@Transactional
	public void deleteStudentFromCourse(String username, String courseName) {
		Student student = entityManager.find(Student.class, username);
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.courseName = :name",Course.class);
		query.setParameter("name", courseName);
		Course course = query.getSingleResult();
		List<Student> studentList = course.getStudentList();
		studentList.remove(student);
		entityManager.merge(student);
		entityManager.merge(course);
	}

	@Override
	public List<Tutor> listTutorByCourseName(String courseName) {
		TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c WHERE c.courseName = :name",Course.class);
		TypedQuery<Course> query1 = query.setParameter("name", courseName);
		Course course = query1.getSingleResult();
		List<Tutor> listTutor = course.getTutorList();
		return listTutor;
	}
	
}











