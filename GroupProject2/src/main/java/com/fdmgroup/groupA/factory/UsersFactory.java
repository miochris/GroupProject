package com.fdmgroup.groupA.factory;

import com.fdmgroup.groupA.model.Admin;
import com.fdmgroup.groupA.model.Student;
import com.fdmgroup.groupA.model.Tutor;
import com.fdmgroup.groupA.model.Users;

public class UsersFactory {
	
	public UsersFactory() {
		super();
	}

	public Users createUser(String firstname, String lastname, String email, String username, String password, double balance, String role) {
		
		Users user = null;
		
	     if(role == null) {
	        return null;
	     } if(role.equalsIgnoreCase("S")) {
	        user = new Student(firstname, lastname, email, username, password, balance, role);
	     } else if(role.equalsIgnoreCase("T")) {
	        user = new Tutor(firstname, lastname, email, username, password, balance, role);
	     } else if(role.equalsIgnoreCase("A")) {
		    user = new Admin(firstname, lastname, email, username, password, balance, role);
	     }
	     return user;
	}
	
}
