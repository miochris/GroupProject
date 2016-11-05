package com.fdmgroup.groupA.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.groupA.model.CourseAddRequest;
import com.fdmgroup.groupA.model.LessonAddRequest;
import com.fdmgroup.groupA.model.StudentRegisterRequest;
import com.fdmgroup.groupA.model.TutorEmployRequest;

public class RequestFactoryTest {
	
	private RequestFactory requestFactory;
	private CourseAddRequest courseAddRequest;
	private LessonAddRequest lessonAddRequest;
	private StudentRegisterRequest studentRegisterRequest;
	private TutorEmployRequest tutorEmployRequest;
	
	@Before
	public void setup(){
		requestFactory = new RequestFactory();
		courseAddRequest = new CourseAddRequest("a","C","c","d");
		lessonAddRequest = new LessonAddRequest("a","L","c","d");
		studentRegisterRequest = new StudentRegisterRequest("a","S","c","d");
		tutorEmployRequest = new TutorEmployRequest("a","T","c","d");
	}
	
	@Test
	public void testCreateRequestReturnsNull(){
		requestFactory.createRequest("a", null, "c", "d");
		Assert.assertEquals(null,requestFactory.createRequest("a", null, "c", "d"));
	}
	
	@Test
	public void testCreateRequestReturnsLessonAddRequest(){
		requestFactory.createRequest("a", "L", "c","d");
		Assert.assertEquals(lessonAddRequest.getUsername(), requestFactory.createRequest("a", "L", "c","d").getUsername());
	}
	
	@Test
	public void testCreateRequestStudentRegisterRequest(){
		requestFactory.createRequest("a", "S", "c","d");
		Assert.assertEquals(studentRegisterRequest.getUsername(), requestFactory.createRequest("a", "S", "c","d").getUsername());
	}
	
	@Test
	public void testCreateRequestReturnsTutorEmployRequest(){
		requestFactory.createRequest("a", "T", "c","d");
		Assert.assertEquals(tutorEmployRequest.getUsername(), requestFactory.createRequest("a", "T", "c","d").getUsername());
	}
	
	@Test
	public void testCreateRequestReturnsCourseAddRequest(){
		requestFactory.createRequest("a", "C", "c","d");
		Assert.assertEquals(courseAddRequest.getUsername(), requestFactory.createRequest("a", "C", "c","d").getUsername());
	}
}
