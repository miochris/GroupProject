package com.fdmgroup.groupA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Request {

	@Id
	@GeneratedValue
	@Column
	private int id;
	@Column
	private String username;
	@Column
	private String description;
	@Column
	private String type;

	public Request(String username, String type, String description) {
		super();
		this.username = username;
		this.description = description;
	}

	public Request() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", username=" + username + ", description=" + description + ", type=" + type + "]";
	}
	
}
