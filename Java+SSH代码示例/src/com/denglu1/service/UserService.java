package com.denglu1.service;

import java.util.List;

import com.denglu1.pojo.User;

public interface UserService {
	
	public void add(User user);
	
	public List<User> getUser(User user);
	
	public User getUser(String sUserName);
	
	public void update(User user);
	
}
