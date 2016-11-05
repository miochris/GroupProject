package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.factory.RequestFactory;
import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.StudentRegisterRequest;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.Users;

public class EnrollControllerTest {

	private EnrollController eController;
	private StudentRegisterRequest studentRequest;
	private Model model;
	private RequestFactory reqFactory;
	private EntityManager entityManager;
	private RequestDAO rDao;
	private CourseDAO cDao;
	private StudentDAO sDao;
	private UserDAO uDao;
	private TutorDAO tDao;
	private GradeDAO gDao;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		model = mock(Model.class);
		rDao = mock(RequestDAO.class);
		cDao = mock(CourseDAO.class);
		sDao = mock(StudentDAO.class);
		uDao = mock(UserDAO.class);
		tDao = mock(TutorDAO.class);
		gDao = mock(GradeDAO.class);
		reqFactory = mock(RequestFactory.class);
		eController = new EnrollController();
		eController.settDao(tDao);
		eController.setrDao(rDao);
		eController.setcDao(cDao);
		eController.setsDao(sDao);
		eController.setuDao(uDao);
		eController.setgDao(gDao);
	}

	@Test
	public void testSendEnrollRequestPass() {
		String username = "a";
		String courseName = "b";
		Course course = new Course(0.0,"b");
		Student student = new Student("","","","a","",11.0,"S");
		when(sDao.getUserByUserName(username)).thenReturn(student);
		when(cDao.getCourseByCourseName(courseName)).thenReturn(course);
		String view = eController.sendEnrollRequest(model, studentRequest, courseName, username);
		verify(rDao).addRequest(studentRequest);
		Assert.assertEquals("protected/student/academy/requestSent", view);
	}

	@Test
	public void testSendEnrollRequestFail() {
		String username = "a";
		String courseName = "b";
		Course course = new Course(2.0,"b");
		Student student = new Student("","","","a","",1.0,"S");
		when(sDao.getUserByUserName(username)).thenReturn(student);
		when(cDao.getCourseByCourseName(courseName)).thenReturn(course);
		String view = eController.sendEnrollRequest(model, studentRequest, courseName, username);
		Assert.assertEquals("protected/student/academy/insufficientFunds", view);
	}

	@Test
	public void testApproveEnrollRequestNoTutor() {
		Date startDate = new Date(2016, 9, 28);
		Date endDate = new Date(2016, 9, 29);
		StudentRegisterRequest request = new StudentRegisterRequest("", "S", "A", "");
		Course course = new Course(startDate, endDate, request.getCourseName(),2.2);
		Student student = new Student("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "S");
		when(rDao.getRequestById(request.getId())).thenReturn(request);
		when(cDao.getCourseByCourseName(request.getCourseName())).thenReturn(course);
		when(sDao.getUserByUserName(request.getUsername())).thenReturn(student);
		String view = eController.approveEnrollRequest(model, request.getId());
		Assert.assertEquals("protected/admin/request/noTutor", view);
	}

	@Test
	public void testApproveEnrollRequest() {
		List<Users> adminList = mock(List.class);
		Tutor tutor1 = new Tutor("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "T");
		Admin admin = new Admin("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "A");
		Lesson lesson = new Lesson();
		lesson.setTutor(tutor1);
		tutor1.getLessonList().add(lesson);
		adminList.add(admin);
		Date startDate = new Date(2016, 9, 28);
		Date endDate = new Date(2016, 9, 29);
		StudentRegisterRequest request = new StudentRegisterRequest("", "S", "A", "");
		Course course = new Course(startDate, endDate, request.getCourseName(),2.2);
		Student student = new Student("bob", "jenkins", "bob.jenkins@fdmgroup.com", "bobjkns", "password", 0, "S");
		course.getTutorList().add(tutor1);
		tutor1.setCourse(course);
		course.getStudentList().add(student);
		student.setCourse(course);
		when(uDao.getUserByUserName("admin")).thenReturn(admin);
		when(rDao.getRequestById(request.getId())).thenReturn(request);
		when(cDao.getCourseByCourseName(request.getCourseName())).thenReturn(course);
		when(sDao.getUserByUserName(request.getUsername())).thenReturn(student);
		String view = eController.approveEnrollRequest(model, request.getId());
		Assert.assertEquals("protected/admin/request/viewRequest", view);
	}
	
	@Test
	public void testRejectEnrollRequest() {
		StudentRegisterRequest request = new StudentRegisterRequest("", "S", "A", "");
		when(rDao.getRequestById(request.getId())).thenReturn(request);
		String view = eController.rejectRequest(model,request.getId());
		Assert.assertEquals("protected/admin/request/responseRequest", view);
	}
}
