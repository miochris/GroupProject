package com.fdmgroup.groupA.dao;

import java.util.List;

import com.fdmgroup.groupA.model.Users;

public interface UserDAO {
	
	public void addUser(Users user);
	public void deleteUserByUsername(String username);
	public void updateUser(Users user);
	public Users getUserByUserName(String username);
	List<Users> getAllUser();
	List<Users> getUserByType(String type);
}
