package com.fdmgroup.groupA.DAO;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fdmgroup.groupA.dao.CourseDAOImpl;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;

public class CourseDAOImplTest {

	private EntityManager mockEM;
	private CourseDAOImpl courseDAOImpl;
	private Course mockCourse;
	private Student mockStudent;
	private TypedQuery<Course> mockQuery;

	@Before
	public void setup() {
		mockEM = Mockito.mock(EntityManager.class);
		mockCourse = Mockito.mock(Course.class);
		mockStudent = Mockito.mock(Student.class);
		mockQuery = Mockito.mock(TypedQuery.class);
		courseDAOImpl = new CourseDAOImpl();
	}

	@Test
	public void testGetCourse() {
		courseDAOImpl.setEntityManager(mockEM);
		courseDAOImpl.getCourse(eq(Mockito.anyInt()));
		verify(mockEM).find(Course.class, eq(Mockito.anyInt()));
	}

	@Test
	public void testGetCourseByCourseName() {
		courseDAOImpl.setEntityManager(mockEM);
		TypedQuery mockQuery1 = mock(TypedQuery.class);
		List<Course> mockList = mock(List.class);
		when(mockEM.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class))
				.thenReturn(mockQuery);
		when(mockQuery.setParameter("name", " ")).thenReturn(mockQuery1);
		when(mockQuery1.getSingleResult()).thenReturn(mockCourse);
		Course result = courseDAOImpl.getCourseByCourseName(" ");
		Assert.assertEquals(mockCourse,result);
	}

	@Test
	public void testAddCourse() {
		courseDAOImpl.setEntityManager(mockEM);
		courseDAOImpl.addCourse(mockCourse);
		verify(mockEM).persist(mockCourse);
	}

	@Test
	public void testUpdateCourse() {
		courseDAOImpl.setEntityManager(mockEM);
		courseDAOImpl.updateCourse(mockCourse);
		verify(mockEM).merge(mockCourse);
	}

	@Test
	public void testRemoveCourse() {
		courseDAOImpl.setEntityManager(mockEM);
		when(mockEM.find(Course.class, eq(anyInt()))).thenReturn(mockCourse);
		courseDAOImpl.deleteCourse(eq(anyInt()));
		verify(mockEM).remove(mockCourse);
	}

	@Test
	public void testListCourses() {
		courseDAOImpl.setEntityManager(mockEM);
		List<Course> mockList = mock(List.class);
		when(mockEM.createQuery("Select c from Course c", Course.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		courseDAOImpl.listCourses();
		Assert.assertEquals(mockList, courseDAOImpl.listCourses());
	}
	
	@Test
	public void testRemoveStudentFromCourse(){
		courseDAOImpl.setEntityManager(mockEM);
		Date date = new Date(2016,10,10);
		TypedQuery<Course> t = mock(TypedQuery.class);
		List<Student> listStudent = new ArrayList<Student>();
		Course course = new Course(date, date, "c", 0);
		Student student = new Student("A", "A", "A", "A", "A", 0, "S");
		listStudent.add(student);
		when(mockEM.find(Student.class, student.getUsername())).thenReturn(student);
		when(mockEM.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class)).thenReturn(t);
		when(t.getSingleResult()).thenReturn(course);
		when(mockCourse.getStudentList()).thenReturn(listStudent);
		courseDAOImpl.deleteStudentFromCourse(student.getUsername(), course.getCourseName());
		listStudent.remove(student);
		verify(mockEM).merge(student);
		verify(mockEM).merge(course);
		
		Assert.assertEquals(0, listStudent.size());
	}
	
	@Test
	public void testListTutorByCourseName(){
		List<Tutor> mockTutor = mock(List.class);
		TypedQuery<Course> mockQuery1 = Mockito.mock(TypedQuery.class);
		courseDAOImpl.setEntityManager(mockEM);
		Course mockCourse = mock(Course.class);
		when(mockEM.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class )).thenReturn(mockQuery);
		when(mockQuery.setParameter("name", "")).thenReturn(mockQuery1);		
		when(mockQuery1.getSingleResult()).thenReturn(mockCourse);
		when(mockCourse.getTutorList()).thenReturn(mockTutor);
		courseDAOImpl.listTutorByCourseName("");
		Assert.assertEquals(mockTutor, courseDAOImpl.listTutorByCourseName(""));
	}
}
