package com.fdmgroup.groupA.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.groupA.controller.FundController;
import com.fdmgroup.groupA.dao.UserDAO;
import com.fdmgroup.groupA.model.Users;

public class FundManage {
	@Autowired
	private UserDAO uDao;
	
	private static final Logger logger = Logger.getLogger(FundController.class);
	public FundManage() {

	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public void deposit(String username, double deposit) {
		logger.debug("Debug starts");
		Users user = uDao.getUserByUserName(username);
		user.deposit(deposit);
		uDao.updateUser(user);
		logger.debug("Debug ends");
	}

	public void pay(String username, double pay) {
		Users user = uDao.getUserByUserName(username);
		user.pay(pay);
		uDao.updateUser(user);
	}

	public void withdraw(String username, double withdraw) {
		double minus = 0 - withdraw;
		Users user = uDao.getUserByUserName(username);
		user.deposit(minus);
		uDao.updateUser(user);
	}
}
