package com.denglu1.service.impl;

import java.util.List;

import com.denglu1.dao.UserDAO;
import com.denglu1.pojo.User;
import com.denglu1.service.UserService;

public class UserServiceImpl implements UserService {

	UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public void add(User user) {
		userDAO.add(user);
	}

	@Override
	public List<User> getUser(User user) {
		return userDAO.getUser(user);
	}
	
	@Override
	public User getUser(String sUserName) {
		return userDAO.getUser(sUserName);
	}


	@Override
	public void update(User user) {
		userDAO.update(user);
	}

}
