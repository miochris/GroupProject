package com.fdmgroup.groupA.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.factory.UsersFactory;
import com.fdmgroup.groupA.model.Users;

@Controller
public class RegisterController {
	
	@Autowired
	@Qualifier("uDao")
	UserDAO uDao;
	@Autowired
	UsersFactory uFactory;

	private static final Logger logger = Logger.getLogger(FundController.class);
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model){
		String url = "register";
		model.addAttribute("user",new Users());
		return url;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(Model model, Users user){
		String url = "registerSuccess";
		Users user1 = uFactory.createUser(user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getUsername(), user.getPassword(), user.getBalance(), user.getRole());
		uDao.addUser(user1);
		model.addAttribute("user",user1);
		model.addAttribute("submit","registerUser");
		return url;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public void setuFactory(UsersFactory uFactory) {
		this.uFactory = uFactory;
	}
	
}
