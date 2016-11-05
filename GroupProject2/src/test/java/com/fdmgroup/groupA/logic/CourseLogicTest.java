package com.fdmgroup.groupA.logic;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.model.Course;

public class CourseLogicTest {

	private CourseDAO cDao;
	private CourseLogic courseLogic;
	
	@Before
	public void setup(){
		cDao = mock(CourseDAO.class);
		courseLogic = new CourseLogic();
		courseLogic.setcDao(cDao);
	}
	
	
	@Test
	public void testChangePrice(){
		Course mockCourse = mock(Course.class);
		when(cDao.getCourse(1)).thenReturn(mockCourse);
		
		courseLogic.changePrice(1, 1.0);
		verify(cDao).updateCourse(mockCourse);
	}
	
	@Test
	public void testCreateCourse(){
		courseLogic.createCourse(" ", "2013-06-12", "2013-06-12", 1.0);
	}
}
