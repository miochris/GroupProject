package com.fdmgroup.groupA.DAO;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fdmgroup.groupA.dao.TutorDAOImpl;
import com.fdmgroup.groupA.model.Tutor;


public class TutorDAOImplTest {
	
	private EntityManager mockEM;
	private TutorDAOImpl tutorDAOImpl;
	private TypedQuery<Tutor> mockQuery;
	
	@Before
	public void init(){
		mockEM=mock(EntityManager.class);
		tutorDAOImpl=new TutorDAOImpl();
		tutorDAOImpl.setEntityManager(mockEM);
		mockQuery = Mockito.mock(TypedQuery.class);
	}
	
	
	@Test 
	public void testFindTutorByLesson(){
		
		TypedQuery<Tutor> mockQuery=mock(TypedQuery.class);
		Tutor mockTutorByLesson=mock(Tutor.class);
		when(mockEM.createQuery(anyString(),eq(Tutor.class))).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockTutorByLesson);
		Tutor result=tutorDAOImpl.findTutorByLesson(eq(anyString()));
		verify(mockEM).createQuery(anyString(),eq(Tutor.class));
		assertEquals(mockTutorByLesson, result);
	
	}
	
	@Test
	public void testGetPercentageByTutorName(){
		TypedQuery<Tutor> mockQuery = mock(TypedQuery.class);
		TypedQuery<Tutor> mockQuery1 = mock(TypedQuery.class);
		Tutor mockTutor = mock(Tutor.class);
		double result = 0.1;
		when(mockEM.createQuery(anyString(), eq(Tutor.class))).thenReturn(mockQuery);
		when(mockQuery.setParameter("username", eq(anyString()))).thenReturn(mockQuery1);
		when(mockQuery.getSingleResult()).thenReturn(mockTutor);
		when(mockTutor.getBalance()).thenReturn(result);
		assertEquals(result, tutorDAOImpl.getPercentageByTutorName(eq(anyString())),0.001); 
		
	}

	
	
	

}
