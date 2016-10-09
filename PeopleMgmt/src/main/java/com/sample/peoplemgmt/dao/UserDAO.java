package com.sample.peoplemgmt.dao;

import com.sample.peoplemgmt.model.User;

public interface UserDAO {
	public User login(String userName, String password); 
}
