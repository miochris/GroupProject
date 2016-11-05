package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.factory.RequestFactory;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.LessonAddRequest;
import com.fdmgroup.groupA.model.Tutor;

public class LessonControllerTest {

	private RequestDAO rDao;
	private CourseDAO cDao;
	private TutorDAO tDao;
	private LessonDAO lDao;
	private RequestFactory reqFactory;
	private LessonController lController;
	private EntityManager entityManager;
	private Model model;
	private Model model2;
	private Model model3;
	private Model model4;
	private HttpServletRequest request;
	private LessonAddRequest lessonAddRequest;
	private HttpSession session;
	private Lesson lesson;
	Tutor tutor;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		model = mock(Model.class);
		model2 = mock(Model.class);
		model3 = mock(Model.class);
		model4 = mock(Model.class);

		request = mock(HttpServletRequest.class);
		session = mock(HttpSession.class);
		rDao = mock(RequestDAO.class);
		cDao = mock(CourseDAO.class);
		tDao = mock(TutorDAO.class);
		lDao = mock(LessonDAO.class);
		reqFactory = mock(RequestFactory.class);
		lController = new LessonController();
		lController.setcDao(cDao);
		lController.setrDao(rDao);
		lController.setlDao(lDao);
		lController.setuDao(tDao);
		tutor = mock(Tutor.class);
		lesson = mock(Lesson.class);
	}

	@Test
	public void testGoToCreateLesson() {
		lessonAddRequest = new LessonAddRequest("", "L", "", "");
		String view = lController.goToCreateLesson(request, model, lessonAddRequest);
		Assert.assertEquals("protected/tutor/lessons/createLesson", view);
	}

	@Test
	public void testCreateLesson() {
		lessonAddRequest = new LessonAddRequest("", "L", "", "");
		String view = lController.CreateLesson(request, model, lessonAddRequest);
		Assert.assertEquals("protected/tutor/lessons/createLessonRequestSent", view);
	}

	@Test
	public void testApproveReq() {
		LessonAddRequest request = new LessonAddRequest("", "L", "", "");
		when(rDao.getRequestById(request.getId())).thenReturn(request);
		String view = lController.ApproveReq(model, 0);
		Assert.assertEquals("protected/admin/request/viewRequest", view);
	}

	@Test
	public void testRejectReq() {
		String view = lController.RejectReq(model, 0);
		Assert.assertEquals("protected/admin/request/viewRequest", view);
	}

	@Test
	public void testDeleteLesson() {
		Tutor tutor = new Tutor("", "", "", "", "", 0.0, "");
		Lesson lesson = new Lesson(tutor, "");
		when(lDao.getLessonByLessonName("")).thenReturn(lesson);
		String view = lController.deleteLesson(model, "");
		Assert.assertEquals("protected/admin/adminView", view);
	}

	@Test
	public void testAssignTutorToLesson() {
		Tutor tutor = new Tutor("", "", "", "", "", 0.0, "");
		Lesson lesson = new Lesson(tutor, "");
		when(lDao.getLessonByLessonName("")).thenReturn(lesson);
		when(tDao.getUserByUserName("")).thenReturn(tutor);
		String view = lController.assignTutorToLesson(model, "", "");
		Assert.assertEquals("", view);
	}

	@Test
	public void testDisplayLessons() {
		Tutor tutor = new Tutor("", "", "", "", "", 0.0, "");
		when(tDao.getUserByUserName("")).thenReturn(tutor);
		String view = lController.displayLessons(model, "");
		Assert.assertEquals("protected/tutor/tutorLesson", view);
	}

	@Test
	public void testTutorQuitLesson() {
		String username = "";
		Tutor tutor = new Tutor("", "", "", "", "", 0.0, "");
		Lesson lesson = new Lesson(tutor, "");
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("lessonId")).thenReturn("0");
		when(lDao.getLesson(0)).thenReturn(lesson);
		when(request.getAttribute("user")).thenReturn(username);
		String view = lController.tutorQuitLesson(request);
		Assert.assertEquals("protected/admin/request/tutorQuitLesson", view);
	}

	@Test
	public void testCreateLessonWithoutReqq() {
		String tutorUsername = "";
		when((Tutor) tDao.getUserByUserName(tutorUsername)).thenReturn(tutor);
		lesson.setTutor(tutor);
		lDao.addLesson(lesson);
		String result = lController.createLessonWithoutReq(model, lesson, tutorUsername);
		Assert.assertEquals("protected/admin/lesson/createLessonSuccess", result);
		verify(lDao, times(2)).addLesson(lesson);
		verify(lesson, times(2)).setTutor(tutor);
	}

	@Test
	public void testGoToCreateLessonWithoutReq() {
		String result = lController.goToCreateLessonWithoutReq(model);
		verify(model).addAttribute("coursesList", cDao.listCourses());
		verify(model).addAttribute("tutorsList", null);
		verify(model, times(3)).addAttribute(anyString(), any());
		Assert.assertEquals("protected/admin/lesson/createLesson", result);
	}
	
	@Test
	public void testChangeLessonName(){
		String lessonName = "";
		int lessonID = 0;
		when((Lesson) lDao.getLesson(lessonID)).thenReturn(lesson);
		lesson.setName(lessonName);
		lDao.updateLesson(lesson);
		String result = lController.changeLessonName(model, lessonName, lessonID);
		Assert.assertEquals("protected/admin/adminView", result);
	}

}
