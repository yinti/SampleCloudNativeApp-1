package com.sample.peoplemgmt.service;

import com.sample.peoplemgmt.model.User;

public interface LoginService {

	public User login(String userName, String password);

	
}
