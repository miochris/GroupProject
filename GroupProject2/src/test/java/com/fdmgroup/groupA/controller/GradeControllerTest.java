package com.fdmgroup.groupA.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;

import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Grade;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;

public class GradeControllerTest {

	private GradeController gController;
	private Model model;
	private UserDAO uDao;
	private LessonDAO lDao;
	private GradeDAO gDao;
	private HttpServletRequest request;
	private List<Grade> gradeList;
	private Lesson lesson;
	private String lessonId;
	private Grade mockGrade;
	private Student mockStudent;

	@Before
	public void setup() {
		model = mock(Model.class);
		uDao = mock(UserDAO.class);
		lDao = mock(LessonDAO.class);
		gDao = mock(GradeDAO.class);
		lesson=mock(Lesson.class);
		request = new MockHttpServletRequest();
		gradeList = mock(List.class);
		mockGrade = mock(Grade.class);
		mockStudent = mock(Student.class);
		gController = new GradeController();
		gController.setgDao(gDao);
		gController.setlDao(lDao);
		gController.setuDao(uDao);
		gController.setLesson(lesson);
	}

	@Test
	public void testViewStudentsInThisClass() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String lessonId = "7";
		when((String) mockRequest.getParameter("lessonId")).thenReturn(lessonId);
		when(gDao.listAllGradesForEachLesson(Integer.parseInt(lessonId))).thenReturn(gradeList);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		Lesson mockLesson = mock(Lesson.class);
		when(lDao.getLesson(Integer.parseInt(lessonId))).thenReturn(mockLesson);

		String result = gController.viewStudentsInThisClass(mockRequest);

		assertEquals("protected/tutor/lessons/viewStudents", result);

	}

	 @Test
	 public void testCreateGrade(){
	 Student student = mock(Student.class);
	 Tutor tutor = new Tutor("", "", "", "", "", 0.0, "");
	 Lesson lesson = new Lesson(tutor, "L");
	 Grade grade = new Grade(student, lesson, 1);
	 gController.setgDao(gDao);
	 gController.setuDao(uDao);
	 gController.setlDao(lDao);
	 List<Grade> gradeList = new ArrayList <Grade>();
	 when(student.getGradeList()).thenReturn(gradeList);
	 Grade grade1 = new Grade();
	 gradeList.add(grade1);
	 when(gDao.getGradeByGradeId(1)).thenReturn(grade);
	 String view = gController.newGradeForStudent(request, 1, 1, model);
	 Assert.assertEquals("protected/tutor/lessons/viewStudents", view);
	 }
}
