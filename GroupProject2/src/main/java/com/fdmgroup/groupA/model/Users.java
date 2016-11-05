package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT_USERS")
public class Users {

	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String email;
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	protected double balance;
	@Column
	private String role;


	public Users(String firstname, String lastname, String email, String username, String password, double balance, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.balance = 0;
		this.role = role;
	}

	public Users() {
		super();
	}

	public void pay(double pay){
		this.balance -= pay; 
	}
	
	public void deposit(double deposit){
		this.balance += deposit;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Users [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}
}
