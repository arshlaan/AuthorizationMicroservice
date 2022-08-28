package com.cognizant.Authorization.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.Authorization.model.User;
import com.cognizant.Authorization.repository.UserDetailsDAO;



@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	//To get User details from DB
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			User user = userDetailsDAO.findByUsername(username);
			return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
	
	}
}
