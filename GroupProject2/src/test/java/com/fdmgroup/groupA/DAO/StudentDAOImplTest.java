package com.fdmgroup.groupA.DAO;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fdmgroup.groupA.dao.StudentDAOImpl;
import com.fdmgroup.groupA.model.Student;


public class StudentDAOImplTest {

	private EntityManager mockEM;
	private StudentDAOImpl studentDAOImpl;
	private Student mockStudent;
	private TypedQuery<Student> mockQuery;
	
	@Before
	public void setup(){
		mockEM = Mockito.mock(EntityManager.class);
		mockStudent = Mockito.mock(Student.class);
		mockQuery = Mockito.mock(TypedQuery.class);
		studentDAOImpl = new StudentDAOImpl();
	}
	@Test
	public void listStudentbyCourseId(){
		ArrayList<Student> mockList = mock(ArrayList.class);
		TypedQuery<Student> mockQuery1 = mock(TypedQuery.class);
		when(mockEM.createQuery("SELECT s FROM Student s WHERE s.course.id =:id",Student.class)).thenReturn(mockQuery);
		when(mockQuery.setParameter("id", eq(anyInt()))).thenReturn(mockQuery1);
		when(mockQuery1.getResultList()).thenReturn(mockList);
		studentDAOImpl.setEntityManager(mockEM);
		studentDAOImpl.listStudentByCourseId(eq(anyInt()));
		Assert.assertEquals(mockList,studentDAOImpl.listStudentByCourseId(eq(anyInt())));
	}
	
}
