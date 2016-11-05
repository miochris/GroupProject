package com.fdmgroup.groupA.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Users;

@Controller
public class FundController {
	
	@Autowired
	@Qualifier("uDao")
	private UserDAO uDao;
	private static final Logger logger = Logger.getLogger(FundController.class);
	public FundController(){
		
	}




	@RequestMapping(value = "deposit", method = RequestMethod.POST)
	public String deposit(Model model, @RequestParam String username, @RequestParam double deposit) {
		logger.debug("Debug starts");
		Users user = uDao.getUserByUserName(username);
		logger.info("Get user: " + user);
		user.deposit(deposit);
		logger.info("User has deposited money");
		uDao.updateUser(user);
		logger.info("Update funds of user: " + user);
		model.addAttribute("user", user);
		logger.debug("Debug ends");
		return "/protected/student/account/balance";
	}

	@RequestMapping(value = "/t1", method = RequestMethod.POST)
	public String pay(Model model, @RequestParam String username, @RequestParam double pay) {
		logger.debug("Debug starts");
		Users user = uDao.getUserByUserName(username);
		user.pay(pay);
		uDao.updateUser(user);
		logger.info("Update funds of user: " + user+" with pay: "+pay);
		model.addAttribute("user", user);
		logger.debug("Debug ends");
		return "";
	}
	
	@RequestMapping(value="/t2", method=RequestMethod.POST)
	public String withdraw(Model model, @RequestParam String username, @RequestParam double withdraw){
		logger.debug("Debug starts");
		double minus=0-withdraw;
		Users user = uDao.getUserByUserName(username);
		user.deposit(minus);
		logger.info("User withdraws money" + user);
		uDao.updateUser(user);
		logger.info("Update details of user: " + user);
		model.addAttribute("user", user);
		logger.debug("Debug ends");
		return"";
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

}
