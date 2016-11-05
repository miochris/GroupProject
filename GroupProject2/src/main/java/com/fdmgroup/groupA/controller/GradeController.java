package com.fdmgroup.groupA.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.dao.LessonDAO;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Grade;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.Student;

@Controller
public class GradeController {

	@Autowired
	private LessonDAO lDao;
	@Autowired
	@Qualifier("uDao")
	private UserDAO uDao;
	@Autowired
	private GradeDAO gDao;
	private Lesson lesson;
	public GradeController() {
		super();
		// TODO Auto-generated constructor stub
	}


//	public GradeController(LessonDAO lDao, UserDAO uDao, GradeDAO gDao, String lessonId, List<Grade> gradeList,
//			Lesson lesson) {
//		super();
//		this.lDao = lDao;
//		this.uDao = uDao;
//		this.gDao = gDao;
//		this.lessonId = lessonId;
//		this.gradeList = gradeList;
//		this.lesson = lesson;
//	}

	private static final Logger logger = Logger.getLogger(FundController.class);



	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public void setlDao(LessonDAO lDao) {
		this.lDao = lDao;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public void setgDao(GradeDAO gDao) {
		this.gDao = gDao;
	}

	@RequestMapping(value = "/viewStudentsInThisClass", method = RequestMethod.GET)
	public String viewStudentsInThisClass(HttpServletRequest request) {
		logger.debug("Debug starts");
		String lessonId = (String) request.getParameter("lessonId");
		logger.info("lessonId  " + lessonId);
		List<Grade> gradeList = gDao.listAllGradesForEachLesson(Integer.parseInt(lessonId));

		lessonId = (String) request.getParameter("lessonId");
		gradeList = gDao.listAllGradesForEachLesson(Integer.parseInt(lessonId));
		request.getSession().setAttribute("gradeList", gradeList);
		lesson = lDao.getLesson(Integer.parseInt(lessonId));
		request.getSession().setAttribute("lesson", lesson);
		logger.debug("Debug ends");
		return "protected/tutor/lessons/viewStudents";
	}

	@RequestMapping(value = "/setStudentGradeForThisLesson", method = RequestMethod.POST)
	public String newGradeForStudent(HttpServletRequest request, @RequestParam int id, @RequestParam int mark,
			Model model) {
		logger.debug("Debug starts");
		logger.info("gradeId  " + id);
		Grade grade = gDao.getGradeByGradeId(id);
		Student student = grade.getStudent();
		grade.setMark(mark);
		double m = 0;
		List<Grade> gList = new ArrayList<Grade>();

		for (Grade g : student.getGradeList()) {
			if (g.getMark() >= 0) {
				gList.add(g);
			}
		}

		for (Grade g : gList) {
			m += g.getMark();
		}

		double avg = m / gList.size();
		student.setMark(avg);
		uDao.updateUser(student);
		gDao.updateGrade(grade);
		logger.info("Update student grade: "+student+ grade);
		request.getSession().setAttribute("gradeList", gDao.listAllGradesForEachLesson((grade.getLesson()).getId()));
		logger.debug("Debug ends");
		return "protected/tutor/lessons/viewStudents";
	}

}
