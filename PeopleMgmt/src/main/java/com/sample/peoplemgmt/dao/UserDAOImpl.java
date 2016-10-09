package com.sample.peoplemgmt.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sample.peoplemgmt.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public User login(String userName, String password) {
		User loggedInUser = null;
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.email = :userName and u.password = :password");
		query.setParameter("userName", userName);
		query.setParameter("password", password);		
		List<User> users = query.list(); 
		
		if(null != users && users.size() == 1){
			loggedInUser = users.get(0); 
			logger.info("Login was successfully, User Details="+ loggedInUser);
		}   
		
		return loggedInUser;
	}



}
