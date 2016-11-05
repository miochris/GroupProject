package com.fdmgroup.groupA.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Admin extends Users {
	
	public Admin() {
		super();
	}

	public Admin(String firstname, String lastname, String email, String username, String password, double balance, String role) {
		super(firstname, lastname, email, username, password, balance, role);

	}
	

	
	
	
	
	
}
