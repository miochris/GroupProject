package com.fdmgroup.groupA.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	private static final Logger logger = Logger.getLogger(FundController.class);
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Authenticate(Model model, HttpServletRequest request) {
		logger.debug("Debug starts");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		logger.debug("Session ends");
		logger.debug("Debug ends");
		return "redirect:index";

	}
}
