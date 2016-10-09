package com.sample.peoplemgmt.service;

import org.springframework.transaction.annotation.Transactional;

import com.sample.peoplemgmt.dao.UserDAO;
import com.sample.peoplemgmt.model.User;

public class LoginServiceImpl implements LoginService {

	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public User login(String userName, String password){
		User loggedInUser = null;
		
		if(null != userName && null != password){
			loggedInUser = userDAO.login(userName, password);
		}
		
		return loggedInUser;
	}

}
