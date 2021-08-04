package com.denglu1.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.denglu1.dao.UserDAO;
import com.denglu1.pojo.User;

public class UserDAOImpl extends HibernateTemplate implements UserDAO {

	@Override
	public void add(User user) {
		save(user);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(User user) {
		
		return findByExample(user);
	}
	
	@Override
	public User getUser(String sUserName) {
		
		 return (User)get(User.class, sUserName);  
	}

	@Override
	public void update(User user) {
		super.update(user);
	}
	
}
