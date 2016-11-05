package com.fdmgroup.groupA.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.DownloadDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.Users;


public class AdminLogicTest {

	private AdminLogic adminLogic;
	private Course course;
	private Tutor tutor;
	private Lesson lesson;
	private CourseDAO cDao;
	private DownloadDAO dDao;
	private UserDAO uDao;
	private Student student ;
	@Before
	public void setup() {
		
		adminLogic = mock(AdminLogic.class);
		cDao = mock(CourseDAO.class);
		uDao = mock(UserDAO.class);
		dDao = mock(DownloadDAO.class);
		course=mock(Course.class);
		student=mock(Student.class);
		adminLogic = new AdminLogic();
		adminLogic.setcDao(cDao);
		adminLogic.setdDao(dDao);
		adminLogic.setuDao(uDao);
		adminLogic.setStudent(student);
		adminLogic.setCourse(course);
	}
	
	@Test
	public void testCreateCourseLessonMap() {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1,2);
		map.put(2,2);
		List<Course> coursesList = new ArrayList<Course>();
		Course course1 = new Course();
		Course course2 = new Course();
		course1.setId(1);
		course2.setId(2);
		coursesList.add(course1);
		coursesList.add(course2);
		List<Tutor> tutorsList1 = new ArrayList<Tutor>();
		Tutor tutor1 = new Tutor();
		Tutor tutor2 = new Tutor();
		tutorsList1.add(tutor1);
		tutorsList1.add(tutor2);
		List<Tutor> tutorsList2 = new ArrayList<Tutor>();
		Tutor tutor3 = new Tutor();
		Tutor tutor4 = new Tutor();
		tutorsList2.add(tutor3);
		tutorsList2.add(tutor4);
		course1.setTutorList(tutorsList1);
		course2.setTutorList(tutorsList2);
		List<Lesson> lessonsList1 = new ArrayList<Lesson>();
		Lesson lesson1 = new Lesson();
		lessonsList1.add(lesson1);
		List<Lesson> lessonsList2 = new ArrayList<Lesson>();
		Lesson lesson2 = new Lesson();
		lessonsList2.add(lesson2);
		List<Lesson> lessonsList3 = new ArrayList<Lesson>();
		Lesson lesson3 = new Lesson();
		lessonsList3.add(lesson3);
		List<Lesson> lessonsList4 = new ArrayList<Lesson>();
		Lesson lesson4 = new Lesson();
		lessonsList4.add(lesson4);
		tutor1.setLesson(lessonsList1);
		tutor2.setLesson(lessonsList2);
		tutor3.setLesson(lessonsList3);
		tutor4.setLesson(lessonsList4);
		when(cDao.listCourses()).thenReturn(coursesList);
		adminLogic.createCourseLessonMap();
		
		Assert.assertEquals(map, adminLogic.createCourseLessonMap());
		
	}
	
	@Test
	public void testChangePassword() {
		Users user = mock(Users.class);
		when(uDao.getUserByUserName("username")).thenReturn(user);
		adminLogic.changePassword("username", "password");
		verify(user).setPassword("password");
		verify(uDao).updateUser(user);
	}
	
	@Test
	public void testQuitCourse() {
		
		String username="sd";
		when((Student) uDao.getUserByUserName(username)).thenReturn(student);
		when(student.getCourse()).thenReturn(course);
		
		adminLogic.quitCourse(username);
		
		verify(student).getCourse();
		verify(uDao).updateUser(student);
		verify(cDao).updateCourse(course);
	}
}
