package com.fdmgroup.groupA.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;

import com.fdmgroup.groupA.dao.LessonDAOImpl;
import com.fdmgroup.groupA.model.Lesson;

public class LessonDAOImplTest {
	
	private EntityManager mockEM;
	private LessonDAOImpl lessonDAOImpl;
	private Lesson mockLesson;
	private TypedQuery<Lesson> mockQuery;
	
	@Before
	public void setup(){
	
		mockEM = Mockito.mock(EntityManager.class);
		mockLesson = Mockito.mock(Lesson.class);
		mockQuery = Mockito.mock(TypedQuery.class);
		lessonDAOImpl = new LessonDAOImpl();
	}
	
	@Test
	public void testGetLesson(){
		lessonDAOImpl.setEntityManager(mockEM);
		lessonDAOImpl.getLesson(eq(Mockito.anyInt()));
		verify(mockEM).find(Lesson.class,eq(Mockito.anyInt()));
	}
	
	@Test
	public void testAddLesson(){
		lessonDAOImpl.setEntityManager(mockEM);
		lessonDAOImpl.addLesson(mockLesson);
		verify(mockEM).persist(mockLesson);
	}
	
	@Test
	public void testUpdateLesson(){
		lessonDAOImpl.setEntityManager(mockEM);
		lessonDAOImpl.updateLesson(mockLesson);
		verify(mockEM).merge(mockLesson);
	}
	
	@Test
	public void testRemoveLesson(){
		lessonDAOImpl.setEntityManager(mockEM);
		when(mockEM.find(Lesson.class, eq(anyInt()))).thenReturn(mockLesson);
		lessonDAOImpl.removeLesson(eq(anyInt()));
		verify(mockEM).remove(mockLesson);
	}
	
	@Test
	public void testListLessons(){
		lessonDAOImpl.setEntityManager(mockEM);
		List<Lesson> mockList = mock(List.class);
		when(mockEM.createQuery("Select l from Lesson l",Lesson.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(mockList);
		lessonDAOImpl.listLessons();
		Assert.assertEquals(mockList,lessonDAOImpl.listLessons());
	}
	
	@Test
	public void testGetLessonByLessonName(){
		lessonDAOImpl.setEntityManager(mockEM);
		TypedQuery mockQuery1 = mock(TypedQuery.class);
		List<Lesson> mockList = mock(List.class);
		when(mockEM.createQuery("Select * from Lesson where name=?", Lesson.class)).thenReturn(mockQuery);
		when(mockQuery.setParameter(1, " ")).thenReturn(mockQuery1);
		when(mockQuery1.getSingleResult()).thenReturn(mockLesson);
		Lesson result = lessonDAOImpl.getLessonByLessonName(" ");
		Assert.assertEquals(mockLesson, result);
	}
}
