package com.fdmgroup.groupA.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;

@Controller
public class AuthenticationController {

	@Autowired
	@Qualifier("uDao")
	private UserDAO uDao;
	@Autowired
	 Logger logger ;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String authenticate(Model model, HttpServletRequest req){
		HttpSession session = req.getSession();

		if (req.isUserInRole("S")){
			session.setAttribute("user", (Student) uDao.getUserByUserName(req.getUserPrincipal().getName()));
			return "index";
		}else if(req.isUserInRole("T")){
			session.setAttribute("user", (Tutor) uDao.getUserByUserName(req.getUserPrincipal().getName()));
			return "index";
		}else if(req.isUserInRole("A")){
			session.setAttribute("user", (Admin) uDao.getUserByUserName(req.getUserPrincipal().getName()));
			return "index";
		}
		return null;
		
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}
	
}
