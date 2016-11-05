package com.fdmgroup.groupA.controllerBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.logic.FundManage;
import com.fdmgroup.groupA.model.Users;

@Controller
public class FundManageController {
	public static final Logger LOG = Logger.getLogger(FundManageController.class);
	@Autowired
	UserDAO uDao;

	@Autowired
	FundManage fundManage;
	Users user;
	Users user2;

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public void setFundManage(FundManage fundManage) {
		this.fundManage = fundManage;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	public void setUser2(Users user2) {
		this.user2 = user2;
	}

	public FundManageController() {

	}

	@RequestMapping(value = "/tutor/withdraw", method = RequestMethod.POST)
	public String withdraw(HttpServletRequest request, @RequestParam double withdraw) {
		 user = (Users) request.getSession().getAttribute("user");
		fundManage.withdraw(user.getUsername(), withdraw);
		request.getSession().setAttribute("user", uDao.getUserByUserName(user.getUsername()));
		 user2 = (Users) request.getSession().getAttribute("user");
		return "redirect:/tutorAccount";
	}

}
