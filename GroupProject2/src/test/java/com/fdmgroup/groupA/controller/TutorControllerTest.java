package com.fdmgroup.groupA.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import static org.mockito.Matchers.*;
import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.dao.TutorDAO;
import com.fdmgroup.groupA.logic.AdminLogic;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.TutorEmployRequest;
import com.fdmgroup.groupA.model.Users;

public class TutorControllerTest {
	
	private TutorController tutorController;
	private TutorDAO tDao;
	private CourseDAO cDao;
	private RequestDAO rDao;
	private StudentDAO sDao;
	private AdminLogic adminLogic;
	private HttpServletRequest req;
	private Model model;
	private Tutor tutor;
	private Tutor tutor2;
	private List<Course> courseList ;
	private  Users user;
	private TutorEmployRequest tutorEmployRequest;
	private Course course;
	private String courseId="77";
	private String lessonId ="66";
	private List<Student> studentList;
	
	
	@Before
	public void setup(){
		tDao=mock(TutorDAO.class);
		cDao=mock(CourseDAO.class);
		rDao=mock(RequestDAO.class);
		sDao=mock(StudentDAO.class);
		adminLogic=mock(AdminLogic.class);
		req=mock(HttpServletRequest.class);
		model = mock(Model.class);
		courseList=mock(List.class);
		user=mock(Users.class);
		course=mock(Course.class);
		tutorEmployRequest=mock(TutorEmployRequest.class);
		tutor = new Tutor("","","","","",10,"T");
		tutor2=mock(Tutor.class);
		tutorController = new TutorController();
		tutorController.setCourseList(courseList);
		tutorController.setcDao(cDao);
		tutorController.setAdminLogic(adminLogic);
		tutorController.setUser(user);
		tutorController.setrDao(rDao);
		tutorController.settDao(tDao);
		tutorController.setCourseId(courseId);
		tutorController.setLessonId(lessonId);
		tutorController.setStudentList(studentList);
		tutorController.setsDao(sDao);
		tutorController.setTutor(tutor2);
	}
	
	
	@Test
	public void testGoToTutorHomeFromIndex(){
		String result=tutorController.goToTutorHomeFromIndex();
		assertEquals("protected/tutor/index", result);
	}
	
	
	@Test 
	public void testGoToTutorAccount(){
		HttpSession session=mock(HttpSession.class);
		when(cDao.listCourses()).thenReturn(courseList);
		when(req.getSession()).thenReturn(session);
		String result=tutorController.goToTutorAccount(req,model);
		assertEquals("/protected/tutor/account/accountHome", result);
	}
	
	@Test
	public void testDeleteTutor() {
		String username = anyString();
		String result = tutorController.deleteTutor(model, username);
		Assert.assertEquals("protected/admin/adminView", result);
		verify(model).addAttribute("tutorsList", tDao.getUserByType("T"));
		verify(model).addAttribute("TutorCVMap", adminLogic.createTutorCVMap());
		verify(model).addAttribute("TutorCVMap", adminLogic.createTutorCVMap());
	}	
	
	@Test
	public void testGoToTutorLessons(){
		String result=tutorController.goToTutorLessons();
		assertEquals("protected/tutor/lessons/lesson", result);
	}
	
	@Test
	public void testChooseCourseRequestSent(){
		HttpSession session=mock(HttpSession.class);
		when(req.getSession()).thenReturn(session);
		when((Users) req.getSession().getAttribute("user")).thenReturn(user);
		String result=tutorController.chooseCourseRequestSent(req, model, tutorEmployRequest);
		assertEquals("protected/tutor/lessons/newCourseRequestSent", result);
		verify(tutorEmployRequest).setUsername(user.getUsername());
		verify(rDao).addRequest(tutorEmployRequest);
		verify(session).getAttribute("user");
	}
	
	@Test
	public void testListTutors(){
		String courseName=anyString();
		when(cDao.getCourseByCourseName(courseName)).thenReturn(course);
		String result=tutorController.listTutors(model, courseName);
		assertEquals("protected/admin/lesson/createLesson", result);
		verify(model).addAttribute("tutorsList", course.getTutorList());
		verify(model).addAttribute("coursesList", cDao.listCourses());
		verify(model).addAttribute("courseName", course.getCourseName());
	}
	
	@Test
	public void testDisplayStudentsByCourseId(){
		when(req.getParameter("courseId")).thenReturn(courseId);
		when(req.getParameter("lessonId")).thenReturn(lessonId);
		when(sDao.listStudentByCourseId(Integer.parseInt(courseId))).thenReturn(studentList);
		HttpSession session=mock(HttpSession.class);
		when(req.getSession()).thenReturn(session);
		String result=tutorController.displayStudentsByCourseId(req);
		assertEquals("protected/tutor/lessons/viewStudents", result);
	}
	@Test
	public void testChangeTutorPercentage(){
		String username="name";
		double percentage=12.22;
		when((Tutor) tDao.getUserByUserName(username)).thenReturn(tutor2);
		String result=tutorController.changeTutorPercentage(model, username, percentage);
		assertEquals("protected/admin/adminView", result);
		verify(model).addAttribute("type", "T");
		verify(model).addAttribute("tutorsList", tDao.getUserByType("T"));
		verify(tutor2).setPercentage(percentage);
		verify(tDao).updateUser(tutor2);
	}
}
