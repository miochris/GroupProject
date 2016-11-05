package com.fdmgroup.groupA.DAO;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.groupA.dao.GradeDAOImpl;
import com.fdmgroup.groupA.model.Grade;

public class GradeDAOImplTest {

	private EntityManager mockEM;
	private GradeDAOImpl gradeDAOImpl;
	private TypedQuery<Grade> mockQuery;
	private Grade mockGrade;

	@Before
	public void init() {
		mockEM = mock(EntityManager.class);
		mockGrade = mock(Grade.class);
		mockQuery = mock(TypedQuery.class);
		gradeDAOImpl = new GradeDAOImpl();
		gradeDAOImpl.setEntityManager(mockEM);
	}

	@Test
	public void testUpdateGrade() {
		gradeDAOImpl.updateGrade(mockGrade);
		verify(mockEM).merge(mockGrade);
	}

	@Test
	public void addNewGrade() {
		gradeDAOImpl.addNewGrade(mockGrade);
		verify(mockEM).persist(mockGrade);
	}

	@Test
	public void testFindGradeByStudentAndLesson() {
		Grade mockGradeResult=mock(Grade.class);
		when(mockEM.createQuery(anyString(), eq(Grade.class))).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockGradeResult);
		Grade result = gradeDAOImpl.findGradeByStudentAndLesson(anyString(), anyString());
		verify(mockEM).createQuery(anyString(), eq(Grade.class));
		assertEquals(result, mockGradeResult);

	}

	@Test
	public void testListAllGradesForEachLesson() {
		List<Grade> mockResult = mock(List.class);
		when(mockEM.createQuery(anyString(), eq(Grade.class))).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResult);
		List<Grade> result = gradeDAOImpl.listAllGradesForEachLesson(eq(anyInt()));
		verify(mockEM).createQuery(anyString(), eq(Grade.class));
		assertEquals(result, mockResult);
	}
	
	@Test
	public void testGetGradeByGradeId() {
		Grade grade = mock(Grade.class);
		when(mockEM.createQuery(anyString(), eq(Grade.class))).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(grade);
		assertEquals(grade, gradeDAOImpl.getGradeByGradeId(eq(anyInt())));
	}
	
	@Test
	public void testFindGradeIDByStudent() {
		List<Grade> mockResult = mock(List.class);
		when(mockEM.createQuery(anyString(), eq(Grade.class))).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockResult);
		List<Grade> result = gradeDAOImpl.findGradeIDByStudent(eq(anyString()));
		verify(mockEM).createQuery(anyString(), eq(Grade.class));
		assertEquals(result, mockResult);
	}
	
	@Test
	public void testRemoveGrade() {
		Grade grade = mock(Grade.class);
		when(mockEM.find(Grade.class,0)).thenReturn(grade);
		gradeDAOImpl.removeGrade(0);
		verify(mockEM).remove(grade);
	}
}
