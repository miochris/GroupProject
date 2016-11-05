package com.fdmgroup.groupA.dao;

import java.util.List;

import com.fdmgroup.groupA.model.Request;

public interface RequestDAO {

	public void addRequest(Request request);
	public void deleteRequest(int id);
	public void updateRequest(Request requst);
	public List<Request> getRequestByUserName(String username);
	public List<Request> getAllRequest();
	public List<Request> getRequestByType(String type);
	public Request getRequestById(int id);
	
}
