package com.fdmgroup.groupA.controller;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.logic.AdminLogic;
import com.fdmgroup.groupA.logic.StudentLogic;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Grade;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Request;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.Users;

public class StudentControllerTest {

	private StudentController studentController;
	private Model model;
	private StudentDAO sDao;
	private Student s = new Student("","","","","",10.0,"S");
	private HttpServletRequest request;
	private HttpSession session;
	private GradeDAO gDao;
	private CourseDAO cDao;
	private RequestDAO rDao;
	Users user;
	Principal principal;
	
	@Before
	public void setup(){
	studentController =new StudentController();
	model = mock(Model.class);
	sDao=mock(StudentDAO.class);
	cDao = mock(CourseDAO.class);
	rDao = mock(RequestDAO.class);
	gDao = mock(GradeDAO.class);
	request = mock(HttpServletRequest.class);
	session=mock(HttpSession.class);
	principal = mock(Principal.class);
	studentController.setsDao(sDao);
	studentController.setcDao(cDao);
	studentController.setrDao(rDao);
	studentController.setgDao(gDao);
	}
	
	@Test
	public void testDeleteStudent(){
		String username ="";
		studentController.setsDao(sDao);
		when(sDao.getUserByUserName(username)).thenReturn(s);
		String del = studentController.deleteStudent(model, username);
		Assert.assertEquals("protected/admin/adminView", del);
	}
	
	@Test
	public void testIfUserNull(){
		//Assert
		Assert.assertEquals("index", studentController.goToStudentHome(request));
		Assert.assertEquals("index", studentController.goToStudentAcademy(model, request));
		Assert.assertEquals("index", studentController.goToStudent(model, request));
		
	}
	
	@Test
	public void testGoToStudentHomeIfUserNotNull() {
		
		Users user = new Users();
		Student student = mock(Student.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		when(request.getUserPrincipal()).thenReturn(principal);
		when(principal.getName()).thenReturn("username");
		when(sDao.getUserByUserName("username")).thenReturn(student);
		//verify(sDao).updateUser(student);
		//verify(session).setAttribute("user",student);
		Assert.assertEquals("/protected/student/index", studentController.goToStudentHome(request));
		
	}
	
	@Test
	public void testGoToStudentAcademyIfUserNotNull() {
		
		Users user = new Users();
		Student student = mock(Student.class);
		Student student1 = mock(Student.class);
		List<Request> reqList = new ArrayList<Request>();
		List<Request> reqList1 = new ArrayList<Request>();

		
		when(rDao.getRequestByUserName("username")).thenReturn(reqList);
		when(rDao.getRequestByUserName("username1")).thenReturn(reqList1);
		when(student.getUsername()).thenReturn("username");
		when(student.getUsername()).thenReturn("username1");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(student);
		
	}
	
	@Test
	public void testQuitCourse(){
		HttpServletRequest request1 = new MockHttpServletRequest();
		Tutor tutor = new Tutor();
		Lesson lesson = new Lesson(tutor, " ");
		Grade grade = new Grade (s,lesson,1);
		List<Grade> gList = new ArrayList<Grade>();
		gList.add(grade);
		Course course = new Course(2.0, " ");
		course.setStudentList(new ArrayList<Student>());
		course.getStudentList().add(s);
		s.setCourse(course);
		when(sDao.getUserByUserName(" ")).thenReturn(s);
		when(gDao.findGradeIDByStudent(s.getUsername())).thenReturn(gList);
		
		String result = studentController.quitCourse(model, " ", request1);
		
		Assert.assertEquals(result, "protected/student/academy/quit");
	}
	
	@Test
	public void testDeleteStudentAccount(){
		HttpSession session1 = new MockHttpSession();
		when(request.getSession()).thenReturn(session1);
		
		String result = studentController.deleteStudentAccount(model, request, " ");
		
		Assert.assertEquals(result, "studentDeleteAccount");
	}
	
	@Test
	public void testDisplayStudentsByCourseId(){
		List<Student> mockList = mock(List.class);
		when(sDao.listStudentByCourseId(1)).thenReturn(mockList);
		
		String result = studentController.displayStudentsByCourseId(model, 1);
	}

	@Test
	public void testGoToStudentBalance(){
		Users user = mock(Users.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		studentController.goToStudent(model, request);
		String result = studentController.goToStudent(model, request);
		
		Assert.assertEquals("/protected/student/account/balance", result);
	}
	
	@Test
	public void testGoToStudentAcademy(){
		Map<Integer, Integer> mockMap = mock(Map.class);
		AdminLogic adminLogic = mock(AdminLogic.class);
		studentController.setAdminLogic(adminLogic);
		List<Request> lReq = new ArrayList<Request>();
		Student student = new Student(" ", " ", " ", " ", " ", 2.0, "S");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(student);
		when(rDao.getRequestByUserName(" ")).thenReturn(lReq);
		when(adminLogic.createCourseLessonMap()).thenReturn(mockMap);
		
		String result = studentController.goToStudentAcademy(model, request);
		
		Assert.assertEquals("/protected/student/academy/academy", result);
	}
	
	@Test
	public void testGoToStudentAcademyReqSizeOne(){
		Map<Integer, Integer> mockMap = mock(Map.class);
		AdminLogic adminLogic = mock(AdminLogic.class);
		studentController.setAdminLogic(adminLogic);
		Request mockReq = mock(Request.class);
		List<Request> lReq = new ArrayList<Request>();
		lReq.add(mockReq);
		Student student = new Student(" ", " ", " ", " ", " ", 2.0, "S");
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(student);
		when(rDao.getRequestByUserName(" ")).thenReturn(lReq);
		when(adminLogic.createCourseLessonMap()).thenReturn(mockMap);
		
		String result = studentController.goToStudentAcademy(model, request);
		
		Assert.assertEquals("/protected/student/academy/academy", result);
	}
	
	@Test
	public void testGoToStudentAcademyStudentHasCourse(){
		StudentLogic studentLogic = mock(StudentLogic.class);
		studentController.setStudentLogic(studentLogic);
		Course course = new Course(1.0," ");
		Lesson lesson = mock(Lesson.class);
		Tutor tutor = new Tutor(" ", " ", " ", " "," ",3.0,"T");
		tutor.setLesson(new ArrayList<Lesson>());
		tutor.getLessonList().add(lesson);
		course.setTutorList(new ArrayList<Tutor>());
		course.getTutorList().add(tutor);
		Map<Integer, Integer> mockMap = mock(Map.class);
		AdminLogic adminLogic = mock(AdminLogic.class);
		studentController.setAdminLogic(adminLogic);
		List<Request> lReq = new ArrayList<Request>();
		Student student = new Student(" ", " ", " ", " ", " ", 2.0, "S");
		student.setCourse(course);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(student);
		when(rDao.getRequestByUserName(" ")).thenReturn(lReq);
		when(adminLogic.createCourseLessonMap()).thenReturn(mockMap);
		
		String result = studentController.goToStudentAcademy(model, request);
		
		Assert.assertEquals("/protected/student/academy/academy", result);
	}
}