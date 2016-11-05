package com.fdmgroup.groupA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.CourseDAO;
import com.fdmgroup.groupA.dao.GradeDAO;
import com.fdmgroup.groupA.dao.RequestDAO;
import com.fdmgroup.groupA.dao.StudentDAO;
import com.fdmgroup.groupA.logic.AdminLogic;
import com.fdmgroup.groupA.logic.StudentLogic;
import com.fdmgroup.groupA.model.Course;
import com.fdmgroup.groupA.model.Grade;
import com.fdmgroup.groupA.model.Lesson;
import com.fdmgroup.groupA.model.LessonComparator;
import com.fdmgroup.groupA.model.Request;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.StudentRegisterRequest;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.Users;

@Controller
public class StudentController {

	@Autowired
	private CourseDAO cDao;
	private LessonComparator lComparator = new LessonComparator();
	private List<Tutor> listTutor;
	@Autowired
	private StudentDAO sDao;
	@Autowired
	private RequestDAO rDao;
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private GradeDAO gDao;
	@Autowired
	private AdminLogic adminLogic;
	
	private static final Logger logger = Logger.getLogger(FundController.class);
	public void setgDao(GradeDAO gDao) {
		this.gDao = gDao;
	}
	
	public void setsDao(StudentDAO sDao) {
		this.sDao = sDao;
	}

	public void setcDao(CourseDAO cDao) {
		this.cDao = cDao;
	}
	
	public void setrDao(RequestDAO rDao) {
		this.rDao = rDao;
	}
	
	public void setAdminLogic(AdminLogic adminLogic) {
		this.adminLogic = adminLogic;
	}
	
	public void setStudentLogic(StudentLogic studentLogic) {
		this.studentLogic = studentLogic;
	}
	@RequestMapping(value = "/studentIndex", method = RequestMethod.GET)
	public String goToStudentHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Users user = null;
		try {
			user = (Users) session.getAttribute("user");

		} catch (Exception e) {

		}
		if (user == null) {
			return "index";
		} else {
		Student student = (Student) sDao.getUserByUserName(request.getUserPrincipal().getName());
		sDao.updateUser(student);
		session.setAttribute("user", student);
			return "/protected/student/index";
		}
		
		
	}

	@RequestMapping(value = "/studentAcademy", method = RequestMethod.GET)
	public String goToStudentAcademy(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		model.addAttribute("request", new StudentRegisterRequest());
		
		
		Users user = null;
		try {
			user = (Users) session.getAttribute("user");

		} catch (Exception e) {

		}		
		if (user == null) {
			return "index";
		} else {

			Student student = (Student) session.getAttribute("user");
			List<Request> listRequest = rDao.getRequestByUserName(student.getUsername());
			if(listRequest.size() != 0){
				int check = 1;
				model.addAttribute("requestCheck",check);
			} else if (student.getCourse() == null) {
				List<Course> listCourse = cDao.listCourses();
				model.addAttribute("courseList", listCourse);
				
				
				model.addAttribute("courseLessonMap", adminLogic.createCourseLessonMap());
				
				
			} else {
				Course course = student.getCourse();
				listTutor = course.getTutorList();
				List<Lesson> listLesson = new ArrayList<Lesson>();
				for (Tutor tutor : listTutor) {
					for (Lesson lesson : tutor.getLessonList()) {
						listLesson.add(lesson);
					}
				}
				listLesson.sort(lComparator);
				model.addAttribute("lessonMapUpload", studentLogic.createStudentGradeMap(student.getUsername()));
				Map<Integer,String> map = studentLogic.createStudentGradeMap(student.getUsername());
				model.addAttribute("studentCourse", course);
				model.addAttribute("lessonList", listLesson);
			}
			return "/protected/student/academy/academy";
		}
	}

	@RequestMapping(value = "/studentBalance", method = RequestMethod.GET)
	public String goToStudent(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users user = null;
		try {
			user = (Users) session.getAttribute("user");

		} catch (Exception e) {

		}
		if (user == null) {
			return "index";
		} else {
		}
		return "/protected/student/account/balance";
	}

	@RequestMapping(value = "/viewStudents")
	public String displayStudentsByCourseId(Model model, int id) {

		List<Student> list = sDao.listStudentByCourseId(id);
		model.addAttribute("studentList", list);
		return "protected/tutor/lessons/viewStudents";
	}
	
	@RequestMapping(value="/deleteStudent", method=RequestMethod.POST)
	public String deleteStudent(Model model, @RequestParam String username){
		//Student student = (Student) sDao.getUserByUserName(username);
		sDao.deleteUserByUsername(username);
		model.addAttribute("type","S");
		model.addAttribute("studentsList",sDao.getUserByType("S"));
		return "protected/admin/adminView";
	}
	
	@RequestMapping(value="/deleteStudentAccount", method=RequestMethod.POST)
	public String deleteStudentAccount(Model model, HttpServletRequest request, @RequestParam String username){
		sDao.deleteUserByUsername(username);
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		return "studentDeleteAccount";
	}
	
	@RequestMapping(value="/quitCourse", method=RequestMethod.POST)
	public String quitCourse(Model model, @RequestParam String username, HttpServletRequest request){
		Student student = (Student) sDao.getUserByUserName(username);
		Course course = student.getCourse();
		course.getStudentList().remove(student);
		student.setCourse(null);
		
		for(Grade grade : gDao.findGradeIDByStudent(student.getUsername())){
			grade.setLesson(null);
			grade.setStudent(null);
			gDao.updateGrade(grade);
			gDao.removeGrade(grade.getId());
		}
		student.setGradeList(null);
		//student.setGradeList(new ArrayList<Grade>());
		sDao.updateUser(student);
		HttpSession session = request.getSession();
		session.setAttribute("user", student);
		return "protected/student/academy/quit";
	}
}
